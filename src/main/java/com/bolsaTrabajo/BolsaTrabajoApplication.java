package com.bolsaTrabajo;

import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import com.bolsaTrabajo.service.PermissionService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.RoleService;
import com.bolsaTrabajo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootApplication
public class BolsaTrabajoApplication implements CommandLineRunner {

	@Autowired
	private SkillCategoryRepository skillCategoryRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	PermissionService permissionService;
	@Autowired
	RoleService roleService;
	@Autowired
	PostulantService postulantService;

	public static void main(String[] args) {
		SpringApplication.run(BolsaTrabajoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception{

		//Permisos, roles y usuario inicial en la aplicacion

		UserService userService;
		//Si el role ADMIN no existe entonces no se ha realizado la configuración inicial
		if(roleService.findByName("ADMIN")==null) {
			/*Permisos para el administrador*/
			//Permisos sobre Usuarios
			Permission ucpermission = createPermissionIfNotExist("USER.CREATE");
			Permission urpermission = createPermissionIfNotExist("USER.READ");
			Permission uepermission = createPermissionIfNotExist("USER.EDIT");
			Permission udpermission = createPermissionIfNotExist("USER.DELETE");
			//Permisos sobre Roles
			Permission rcpermission = createPermissionIfNotExist("ROLE.CREATE");
			Permission rrpermission = createPermissionIfNotExist("ROLE.READ");
			Permission repermission = createPermissionIfNotExist("ROLE.EDIT");
			Permission rdpermission = createPermissionIfNotExist("ROLE.DELETE");
			//Permisos
			Permission pcpermission = createPermissionIfNotExist("PERMISSION.CREATE");
			Permission prpermission = createPermissionIfNotExist("PERMISSION.READ");
			Permission pepermission = createPermissionIfNotExist("PERMISSION.EDIT");
			Permission pdpermission = createPermissionIfNotExist("PERMISSION.DELETE");

			/*Permisos para el Postulante*/
			//Permisos sobre Postulantes
			Permission pocpermission = createPermissionIfNotExist("POSTULANT.CREATE");
			Permission porpermission = createPermissionIfNotExist("POSTULANT.READ");
			Permission poepermission = createPermissionIfNotExist("POSTULANT.EDIT");
			Permission podpermission = createPermissionIfNotExist("POSTULANT.DELETE");

			/*Permisos para Empresa*/
			//Permisos sobre empresa
			Permission ccpermission = createPermissionIfNotExist("COMPANY.CREATE");
			Permission crpermission = createPermissionIfNotExist("COMPANY.READ");
			Permission cepermission = createPermissionIfNotExist("COMPANY.EDIT");
			Permission cdpermission = createPermissionIfNotExist("COMPANY.DELETE");

			Set<Permission> adminPermission = new HashSet<>(Arrays.asList(ucpermission, urpermission, uepermission, udpermission,
					rcpermission, rrpermission, repermission, rdpermission,
					pcpermission, prpermission, pepermission, pdpermission,
					pocpermission, porpermission, poepermission, podpermission,
					ccpermission, crpermission, cepermission, cdpermission));

			Set<Permission> postulantPermission = new HashSet<>(Arrays.asList(pocpermission, porpermission, poepermission, podpermission));
			Set<Permission> companyPermission = new HashSet<>(Arrays.asList(ccpermission, crpermission, cepermission, cdpermission));
			createRoleIfNotFound("ADMIN", adminPermission);
			createRoleIfNotFound("POSTULANTE", postulantPermission);
			createRoleIfNotFound("EMPRESA", companyPermission);
			createUserIfNotFound("administrador");
		}

		SkillCategory category1 = new SkillCategory(
				"Locomotrices","HLOC"
		);

		SkillCategory category2 = new SkillCategory(
				"Intelectuales","HINT"
		);

		SkillCategory category3 = new SkillCategory(
				"Sociales","HSOC"
		);

		SkillCategory category4 = new SkillCategory(
				"Gerenciales","HGER"
		);
		SkillCategory category5 = new SkillCategory(
				"Linguisticas","HLIN"
		);
		if (skillCategoryRepository.count()<0){
			skillCategoryRepository.save(category1);
			skillCategoryRepository.save(category2);
			skillCategoryRepository.save(category3);
			skillCategoryRepository.save(category4);
		}


	}

	@Transactional
	private Permission createPermissionIfNotExist(String name){
		Permission permission = permissionService.findByName(name);
		if (permission == null){
			permission = new Permission(name);
			permissionService.save(permission);
		}
		return permission;
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Set<Permission> permissions){
		Role role = roleService.findByName(name);
		if (role==null){
			role = new Role(name);
			role.setPermissions(permissions);
			roleService.save(role);
		}
		return role;
	}

	@Transactional
	private void createUserIfNotFound(String username) throws ParseException {
		Postulant postulant = new Postulant();
		postulant.setName("admin");
		postulant.setLastName("admin");
		postulant.setUsername("administrador");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String dateInString = "31-08-1982 10:20:56";
		Date date = sdf.parse(dateInString);
		postulant.setBirthday(date);
		postulant.setPassword(bCryptPasswordEncoder.encode("12345678"));
		postulant.setPasswordConfirm("12345678");
		postulant.setActive(1);
		HashSet<Role> roleCollection = new HashSet<>(roleService.getAllRoles());
		postulant.setRoles(roleCollection);
		postulantService.save(postulant);
	}
}
