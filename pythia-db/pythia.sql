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

-- object: project.project | type: TABLE --
-- DROP TABLE IF EXISTS project.project CASCADE;
CREATE TABLE project.project(
	id bigint NOT NULL,
	hansuprojectid varchar,
	name varchar,
	description varchar,
	created_at timestamp,
	created_by varchar,
	updated_at timestamp,
	updated_by varchar,
	CONSTRAINT projectid_pri PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE project.project OWNER TO pythiaservice;
-- ddl-end --


-- object: project.plan | type: TABLE --
-- DROP TABLE IF EXISTS project.plan CASCADE;
CREATE TABLE project.plan(
	id bigint NOT NULL,
	projectid smallint,
	mainno smallint,
	subno smallint,
	version varchar,
	created_at timestamp,
	created_by varchar,
	updated_at timestamp,
	updated_by varchar,
	CONSTRAINT planid_pri PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE project.plan OWNER TO pythiaservice;
-- ddl-end --



-- object: project_index | type: INDEX --
-- DROP INDEX IF EXISTS project.project_index CASCADE;
CREATE INDEX project_index ON project.project
	USING btree
	(
	  id
	);
-- ddl-end --

-- object: plan_id | type: INDEX --
-- DROP INDEX IF EXISTS project.plan_id CASCADE;
CREATE INDEX plan_id ON project.plan
	USING btree
	(
	  id
	);
-- ddl-end --

-- object: projecid_for | type: CONSTRAINT --
-- ALTER TABLE project."Plan" DROP CONSTRAINT IF EXISTS projecid_for CASCADE;
ALTER TABLE project.plan ADD CONSTRAINT projecid_for FOREIGN KEY (projectid)
REFERENCES project.project (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


