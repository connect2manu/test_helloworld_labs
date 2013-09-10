--<ScriptOptions statementTerminator=";"/>

CREATE TABLE referencetypes (
	type VARCHAR(10) NOT NULL,
	description VARCHAR(100),
	PRIMARY KEY (type)
) ENGINE=InnoDB;

CREATE TABLE prikborditems (
	id INT NOT NULL,
	title VARCHAR(30) NOT NULL,
	parentid INT NOT NULL,
	prikbordid INT NOT NULL,
	referencetype VARCHAR(10) NOT NULL,
	depth INT NOT NULL,
	foldertype VARCHAR(10),
	contenttype VARCHAR(10),
	parentalrating VARCHAR(10),
	posterfile VARCHAR(100),
	reference VARCHAR(100),
	description VARCHAR(100),
	lastupdatedatetime DATETIME NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE contenttypes (
	type VARCHAR(10) NOT NULL,
	description VARCHAR(100),
	PRIMARY KEY (type)
) ENGINE=InnoDB;

CREATE TABLE prikbord (
	id INT NOT NULL,
	title VARCHAR(30),
	accountnumber INT NOT NULL,
	parentalrating VARCHAR(10),
	posterfile VARCHAR(100),
	lastupdatedatetime DATETIME NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE foldertypes (
	type VARCHAR(10) NOT NULL,
	description VARCHAR(100),
	PRIMARY KEY (type)
) ENGINE=InnoDB;

CREATE INDEX fk_pbitems_foldertypes_idx ON prikborditems (foldertype ASC);

CREATE UNIQUE INDEX accountnumber_UNIQUE ON prikbord (accountnumber ASC);

CREATE INDEX fk_pbitems_contenttypes_idx ON prikborditems (contenttype ASC);

CREATE INDEX fk_pbitems_referencetypes_idx ON prikborditems (referencetype ASC);

CREATE INDEX fk_pbitems_prikbord_idx ON prikborditems (prikbordid ASC);

ALTER TABLE prikborditems ADD CONSTRAINT fk_pbitems_referencetypes FOREIGN KEY (referencetype)
	REFERENCES referencetypes (type);

ALTER TABLE prikborditems ADD CONSTRAINT fk_pbitems_foldertypes FOREIGN KEY (foldertype)
	REFERENCES foldertypes (type);

ALTER TABLE prikborditems ADD CONSTRAINT fk_pbitems_contenttypes FOREIGN KEY (contenttype)
	REFERENCES contenttypes (type);

ALTER TABLE prikborditems ADD CONSTRAINT fk_pbitems_prikbord FOREIGN KEY (prikbordid)
	REFERENCES prikbord (id)
	ON DELETE CASCADE
	ON UPDATE CASCADE;

