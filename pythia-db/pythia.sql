CREATE SCHEMA project;
-- ddl-end --
ALTER SCHEMA project OWNER TO pythiaservice;
-- ddl-end --

-- ddl-end --
GRANT CONNECT, CREATE
 ON DATABASE pythia TO pythiaservice;

-- -- ddl-end --        
GRANT SELECT, INSERT, UPDATE, DELETE
 ON ALL TABLES IN SCHEMA public TO pythiaservice;
-- -- ddl-end --

SET search_path TO pg_catalog,public,project;
-- ddl-end --

-- Create sequence for project table id
CREATE SEQUENCE project.proj_serial;

-- Create sequence for plan table id
CREATE SEQUENCE project.plan_serial;

-- object: project."Project" | type: TABLE --
-- DROP TABLE IF EXISTS project."Project" CASCADE;
CREATE TABLE project."Project"(
	id bigint NOT NULL DEFAULT nextval('proj_serial'),
	hansuprojectid varchar,
	name varchar,
	description varchar,
	"createdAt" varchar,
	"createdBy" timestamp,
	"updatedAt" varchar,
	"updatedBy" timestamp,
	CONSTRAINT projectid_pri PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE project."Project" OWNER TO pythiaservice;
-- ddl-end --

-- object: project."Plan" | type: TABLE --
-- DROP TABLE IF EXISTS project."Plan" CASCADE;
CREATE TABLE project."Plan"(
	id bigint NOT NULL DEFAULT nextval('plan_serial'),
	projectid smallint,
	mainno smallint,
	subno smallint,
	version varchar,
	"createdAt" varchar,
	"createdBy" timestamp,
	"updatedAt" varchar,
	"updatedBy" timestamp,
	CONSTRAINT planid_pri PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE project."Plan" OWNER TO pythiaservice;
-- ddl-end --

-- object: project_index | type: INDEX --
-- DROP INDEX IF EXISTS project.project_index CASCADE;
CREATE INDEX project_index ON project."Project"
	USING btree
	(
	  id
	);
-- ddl-end --

-- object: plan_id | type: INDEX --
-- DROP INDEX IF EXISTS project.plan_id CASCADE;
CREATE INDEX plan_id ON project."Plan"
	USING btree
	(
	  id
	);
-- ddl-end --

-- object: projecid_for | type: CONSTRAINT --
-- ALTER TABLE project."Plan" DROP CONSTRAINT IF EXISTS projecid_for CASCADE;
ALTER TABLE project."Plan" ADD CONSTRAINT projecid_for FOREIGN KEY (projectid)
REFERENCES project."Project" (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


