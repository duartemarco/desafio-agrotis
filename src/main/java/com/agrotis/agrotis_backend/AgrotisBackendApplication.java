package com.agrotis.agrotis_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class AgrotisBackendApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(AgrotisBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateDatabase() {
		return args -> {
			String insertLaboratorio = "INSERT INTO tbl_laboratorio (id, nome) VALUES (1, 'Lab 1'), (2, 'Lab 2'), (3, 'Lab 3'), (4, 'Lab 4'), (5, 'Lab 5') ON CONFLICT (id) DO NOTHING;";
			String insertPropriedade = "INSERT INTO tbl_propriedade (id, nome) VALUES (1, 'Propriedade 1'), (2, 'Propriedade 2'), (3, 'Propriedade 3'), (4, 'Propriedade 4'), (5, 'Propriedade 5'), (6, 'Propriedade 6') ON CONFLICT (id) DO NOTHING;";

			jdbcTemplate.execute(insertLaboratorio);
			jdbcTemplate.execute(insertPropriedade);
		};
	}
}
