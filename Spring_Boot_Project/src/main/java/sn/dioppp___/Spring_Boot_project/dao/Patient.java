package sn.dioppp___.Spring_Boot_project.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name="PATIENTS")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements Serializable {
	@Id @GeneratedValue
	long id;
	@Column(name="Nom")
	@NonNull
	String nom;
	@NonNull
	Date dateNaissance;
	@Column(name="Score")
	int score;
	@Column(name="Malade")
	boolean malade;
}

