package com.transformr.unit.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.transformr.unit.enums.UnitType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "unit")
@NoArgsConstructor
@AllArgsConstructor
public class Unit {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "unit", nullable = false)
	private String unit;

	@Column(name = "type", nullable = false)
	private UnitType type;

	@Column(name = "value", nullable = false)
	private double value;

	@Column(name = "base", nullable = false)
	private boolean base;

	@Column(name = "fromFormula", nullable = true)
	private String fromFormula;

	@Column(name = "toFormula", nullable = true)
	private String toFormula;

	public Unit(String name, String unit, UnitType type, double value, boolean base, String fromFormula, String toFormula) {
		this.name = name;
		this.unit = unit;
		this.type = type;
		this.value = value;
		this.fromFormula = fromFormula;
		this.toFormula = toFormula;
		this.base = base;
	}
}
