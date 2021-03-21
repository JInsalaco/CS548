--<ScriptOptions statementTerminator=";"/>

CREATE TABLE radiologytreatment_treatmentdates (
		radiologytreatment_id INT8,
		treatmentdates DATE
	);

CREATE TABLE provider (
		id INT8 NOT NULL,
		name VARCHAR(255),
		npi INT8,
		providertype INT4
	);

CREATE TABLE drugtreatment (
		id INT8 NOT NULL,
		dosage FLOAT8,
		drug VARCHAR(255)
	);

CREATE TABLE surgerytreatment (
		id INT8 NOT NULL,
		surgerydate DATE
	);

CREATE TABLE treatment (
		id INT8 NOT NULL,
		ttype VARCHAR(31),
		diagnosis VARCHAR(255),
		patient_fk INT8,
		provider_fk INT8
	);

CREATE TABLE radiologytreatment (
		id INT8 NOT NULL
	);

CREATE TABLE patient (
		id INT8 NOT NULL,
		name VARCHAR(255),
		patientid INT8
	);

CREATE TABLE sequence (
		seq_name VARCHAR(50) NOT NULL,
		seq_count NUMERIC(38 , 0)
	);

CREATE UNIQUE INDEX surgerytreatment_pkey ON surgerytreatment (id ASC);

CREATE UNIQUE INDEX treatment_pkey ON treatment (id ASC);

CREATE UNIQUE INDEX drugtreatment_pkey ON drugtreatment (id ASC);

CREATE UNIQUE INDEX provider_pkey ON provider (id ASC);

CREATE UNIQUE INDEX sequence_pkey ON sequence (seq_name ASC);

CREATE UNIQUE INDEX radiologytreatment_pkey ON radiologytreatment (id ASC);

CREATE UNIQUE INDEX patient_pkey ON patient (id ASC);

ALTER TABLE patient ADD CONSTRAINT patient_pkey UNIQUE (id);

ALTER TABLE provider ADD CONSTRAINT provider_pkey UNIQUE (id);

ALTER TABLE treatment ADD CONSTRAINT treatment_pkey UNIQUE (id);

ALTER TABLE surgerytreatment ADD CONSTRAINT fk_surgerytreatment_id FOREIGN KEY (id)
	REFERENCES treatment (id);

ALTER TABLE radiologytreatment_treatmentdates ADD CONSTRAINT fk_radiologytreatment_treatmentdates_radiologytreatment_id FOREIGN KEY (radiologytreatment_id)
	REFERENCES treatment (id);

ALTER TABLE treatment ADD CONSTRAINT fk_treatment_provider_fk FOREIGN KEY (provider_fk)
	REFERENCES provider (id);

ALTER TABLE radiologytreatment ADD CONSTRAINT fk_radiologytreatment_id FOREIGN KEY (id)
	REFERENCES treatment (id);

ALTER TABLE treatment ADD CONSTRAINT fk_treatment_patient_fk FOREIGN KEY (patient_fk)
	REFERENCES patient (id);

ALTER TABLE drugtreatment ADD CONSTRAINT fk_drugtreatment_id FOREIGN KEY (id)
	REFERENCES treatment (id);

