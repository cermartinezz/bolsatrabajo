package com.bolsaTrabajo;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import com.bolsaTrabajo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class BolsaTrabajoApplication implements CommandLineRunner {

	@Autowired
	private SkillCategoryRepository skillCategoryRepository;

	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(BolsaTrabajoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception{

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

		Role role = new Role("POSTULANTE");
		Role role2 = new Role("EMPRESA");

		if(roleService.count()<=0){
			roleService.save(role);
			roleService.save(role2);
		}

	}
}
