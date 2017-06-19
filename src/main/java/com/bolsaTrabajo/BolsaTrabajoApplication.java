package com.bolsaTrabajo;

import com.bolsaTrabajo.model.*;
import com.bolsaTrabajo.model.catalog.Language;
import com.bolsaTrabajo.model.catalog.LanguageLevel;
import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.bolsaTrabajo.util.Gender.Masculino;

@SpringBootApplication
public class BolsaTrabajoApplication implements CommandLineRunner {

	@Autowired
	private SkillCategoryService skillCategoryService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	PermissionService permissionService;
	@Autowired
	RoleService roleService;
	@Autowired
	PostulantService postulantService;

	@Autowired
	private SkillService skillService;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private LanguageLevelService languageLevelService;

	public static void main(String[] args) {
		SpringApplication.run(BolsaTrabajoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception{


		if (languageService.count() <=0) {
			insertLanguages();
		}

		if (languageLevelService.count() <=0) {
			insertLanguageLevels();
		}

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
				"Locomotrices", "HLOC"
		);

		SkillCategory category2 = new SkillCategory(
				"Intelectuales", "HINT"
		);

		SkillCategory category3 = new SkillCategory(
				"Sociales", "HSOC"
		);

		SkillCategory category4 = new SkillCategory(
				"Gerenciales", "HGER"
		);
		SkillCategory category5 = new SkillCategory(
				"Linguisticas", "HLIN"
		);
		Skill skill = new Skill("Nose 1", "nose1", category1);
		Skill skill2 = new Skill("Nose 2", "nose2", category2);
		Skill skill3 = new Skill("Nose 3", "nose3", category3);
		Skill skill4 = new Skill("Nose 4", "nose4", category4);
		Skill skill5 = new Skill("Nose algo", "nose_2", category1);

		if (skillCategoryService.count() <= 0) {
			skillCategoryService.save(category1);
			skillCategoryService.save(category2);
			skillCategoryService.save(category3);
			skillCategoryService.save(category4);
			skillService.storeSkill(skill);
			skillService.storeSkill(skill2);
			skillService.storeSkill(skill3);
			skillService.storeSkill(skill4);
			skillService.storeSkill(skill5);
		}

	}

	private void insertLanguageLevels() {
		LanguageLevel lvl1 = new LanguageLevel(
			"Basico","A1"
		);
		LanguageLevel lvl2 = new LanguageLevel(
				"Elemental","A2"
		);
		LanguageLevel lvl3 = new LanguageLevel(
				"Pre-Intermedio","B1"
		);
		LanguageLevel lvl4 = new LanguageLevel(
				"Intermedio Superior","B2"
		);
		LanguageLevel lvl5 = new LanguageLevel(
				"Avanzado","C1"
		);
		LanguageLevel lvl6 = new LanguageLevel(
				"Superior","C2"
		);
		languageLevelService.store(lvl1);
		languageLevelService.store(lvl2);
		languageLevelService.store(lvl3);
		languageLevelService.store(lvl4);
		languageLevelService.store(lvl5);
		languageLevelService.store(lvl6);

	}

	private void insertLanguages(){
		Language language1 = new Language(
				"ES","Español"
		);

		Language language2 = new Language(
				"EN","Inglés"
		);

		Language language3 = new Language(
				"IT","Italiano"
		);

		Language language4 = new Language(
				"DE","Alemán"
		);

		Language language5 = new Language(
				"JA","Japonés"
		);

		languageService.store(language1);
		languageService.store(language2);
		languageService.store(language3);
		languageService.store(language4);
		languageService.store(language5);



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
		String dateInString = "01-01-2000 00:00:00";
		Date date = sdf.parse(dateInString);
		postulant.setBirthday(date);
		postulant.setDui("22222222-0");
		postulant.setNit("2222-222222-222-2");
		postulant.setGender(Masculino);
		postulant.setNup("111111111111");
		postulant.setPassport("A22222222");
		postulant.setPassword(bCryptPasswordEncoder.encode("12345678"));
		postulant.setPasswordConfirm("12345678");
		postulant.setEmail("admin@correo.com");

		postulant.setActive(1);
		HashSet<Role> roleCollection = new HashSet<>(roleService.getAllRoles());
		postulant.setRoles(roleCollection);
		postulantService.save(postulant);
	}
}
