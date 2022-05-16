package ec.uisrael.sisreha.com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.uisrael.sisreha.com.dto.UserDto;
import ec.uisrael.sisreha.com.entity.User;
import ec.uisrael.sisreha.com.mapper.IUserMapper;
import ec.uisrael.sisreha.com.service.IRoleUserService;
import ec.uisrael.sisreha.com.service.IUserService;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping("/api")
@Slf4j
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleUserService roleUserService;
	@Autowired
	private IUserMapper mapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private Map<String, Object> response;

	public UserController() {
		response = new HashMap<>();
	}

	//@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/users")
	public ResponseEntity<?> findAll() {
		response = new HashMap<>();
		try {
			List<User> users = userService.findAll();
			if (!users.isEmpty())
				return ResponseEntity.ok().body(mapper.entitiesToDtos(users));
			else {
				response.put("mensaje", "No existen registros");
				response.put("error", "No existen registros");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			log.error("findAll: ", e);
			response.put("mensaje", "Error al buscar los registros");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//@Secured({ "ROLE_ADMIN" })
	@PostMapping("/users")
	public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto, BindingResult result) {
		response = new HashMap<>();
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
						.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
			User user = mapper.dtoToEntity(userDto);
			user = userService.save(user);
			response.put("mensaje", "El usuario ha sido creado con éxito");
			response.put("user", mapper.entityToDto(user));
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (DataAccessException e) {
			log.error("create: ", e);
			response.put("mensaje", "Error al guardar el registro");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//@Secured({ "ROLE_ADMIN" })
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody UserDto userDto, BindingResult result, @PathVariable Long id) {
		response = new HashMap<>();
		User user;
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
						.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			user = userService.findById(id).orElse(null);
			if (user == null) {
				response.put("mensaje", "El usuario no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			} else {
				userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
				user.setUsername(userDto.getUsername());
				user.setPassword(userDto.getPassword());
				user.setEnabled(userDto.getEnabled());
				user = userService.save(user);
				response.put("mensaje", "El usuario ha sido actualizado  con éxito");
				response.put("user", mapper.entityToDto(user));

				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}
		} catch (DataAccessException e) {
			log.error("create: ", e);
			response.put("mensaje", "Error al actualizar el registro");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		response = new HashMap<>();
		try {
			Optional<User> user = userService.findById(id);
			if (user.isPresent()) {
				roleUserService.findByUser(user.get()).stream()
						.forEach(ru -> roleUserService.deleteById(ru.getRolUsuarioId()));

				userService.delete(user.get());
				response.put("mensaje", "El usuario ha sido eliminado con éxito");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.put("mensaje", "El usuario no existe");
				response.put("error", "El usuario no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
