package com.RentalBuilding.API;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.DTO.UserDTO;
import com.RentalBuilding.Entity.RoleEntity;
import com.RentalBuilding.Entity.UserEntity;
import com.RentalBuilding.Exception.ExistedException;
import com.RentalBuilding.Exception.FieldRequiredException;
import com.RentalBuilding.Exception.NotExistedException;
import com.RentalBuilding.Exception.ReferenceException;
import com.RentalBuilding.Service.UserService;
import com.RentalBuilding.Utils.Validate;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<ResponseDTO> saveUser(@RequestParam("img") MultipartFile file,
			@RequestParam("data") String jsonData) {
		UserDTO user = null;
		try {
			user = objectMapper.readValue(jsonData, UserDTO.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (!Validate.UserValidate(user)) {
			throw new FieldRequiredException();
		}
		if (user != null && userService.findByUserCode(user.getUserCode()) != null) {
			throw new ExistedException();
		} else {
			ResponseDTO result = new ResponseDTO(null, userService.save(user, file));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateUser(@RequestParam(name = "img", required = false) MultipartFile file,
			@RequestParam("data") String jsonData, @PathVariable Long id) {
		UserDTO user = null;
		if (userService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			try {
				user = objectMapper.readValue(jsonData, UserDTO.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			if (!Validate.UserValidate(user)) {
				throw new FieldRequiredException();
			}
			user.setId(id);
			ResponseDTO result = new ResponseDTO(null, userService.save(user, file));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteUser(@PathVariable Long id) {
		if (userService.findById(id) == null) {
			throw new NotExistedException();
		}
		if (userService.checkContractsOfUser(id).size() > 0) {
			throw new ReferenceException();
		} else {
			userService.deleteUser(id);
			ResponseDTO result = new ResponseDTO(null);
			result.setData("");
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}

	}

	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getListUsers(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		ResponseDTO result = new ResponseDTO();
		if (page == null && limit == null) {
			result.setError(null);
			result.setData(userService.getListUsers());
		} else {
			Pageable pageable = PageRequest.of(page - 1, limit);
			result.setError(null);
			result.setData(userService.getListUsersWithPaging(pageable));
		}
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseDTO> getUserById(@PathVariable Long id) {
		if (userService.findById(id) == null) {
			throw new NotExistedException();
		}
		ResponseDTO result = new ResponseDTO(null, userService.findById(id));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}

	@GetMapping("/refreshToke")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String header = request.getHeader(AUTHORIZATION);
		if (header != null && header.startsWith("Bearer ")) {
			try {
				String token = header.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = jwtVerifier.verify(token);
				String userName = decodedJWT.getSubject();
				UserEntity user = userService.findByUserEmail(userName);
				List<String> list = new ArrayList<>();
				for (RoleEntity item : user.getRoles()) {
					list.add((item.getRoleCode()));
				}
				String accessToken = JWT.create().withSubject(userName)
						.withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString()).withClaim("roles", list).sign(algorithm);
				response.setHeader("token", accessToken);
				response.setHeader("referh", header);
			} catch (Exception e) {
				response.setHeader("error", e.getMessage());
				response.setStatus(FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage());
				response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else {
			throw new RuntimeException("Refresh token is missing");
		}
	}

	@GetMapping("/findAllTechStaff")
	public ResponseEntity<ResponseDTO> findAllTechStaff() {
		ResponseDTO result = new ResponseDTO(null, userService.findAllTechStaff());
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}

	@GetMapping("/searchByField")
	public ResponseEntity<ResponseDTO> searchByField(
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "userCode", required = false) String userCode,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "departmentCode", required = false) String departmentCode) {
		ResponseDTO result = new ResponseDTO(null,
				userService.searchByField(userName, userCode, userEmail, departmentCode));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}

}
