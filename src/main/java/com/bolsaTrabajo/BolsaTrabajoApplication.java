package com.bolsaTrabajo;

import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.SkillCategory;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import com.bolsaTrabajo.service.PermissionService;
import com.bolsaTrabajo.service.RoleService;
import com.bolsaTrabajo.service.UserService;
import netscape.security.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.*;

@SpringBootApplication
public class BolsaTrabajoApplication implements CommandLineRunner {

	@Autowired
	private SkillCategoryRepository skillCategoryRepository;

	@Autowired
	PermissionService permissionService;
	@Autowired
	RoleService roleService;

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
}
