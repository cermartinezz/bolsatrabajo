package com.bolsaTrabajo;

import com.bolsaTrabajo.model.Language;
import com.bolsaTrabajo.model.SkillCategory;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import com.bolsaTrabajo.service.LanguageService;
import com.bolsaTrabajo.service.SkillCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class BolsaTrabajoApplication implements CommandLineRunner {


	@Autowired
	private SkillCategoryService skillCategoryService;

	@Autowired
	private LanguageService languageService;

	public static void main(String[] args) {
		SpringApplication.run(BolsaTrabajoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception{


		if(skillCategoryService.getAllSkillsCategory() == null){
			insertCategories();
		}


		if(languageService.getAllLanguages() == null){
			insertLanguages();

		}


	}

	private void insertCategories(){
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

		skillCategoryService.store(category1);
		skillCategoryService.store(category2);
		skillCategoryService.store(category3);
		skillCategoryService.store(category4);
		skillCategoryService.store(category5);
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

}
