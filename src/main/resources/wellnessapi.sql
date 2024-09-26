CREATE TABLE geo (
                     id BIGSERIAL PRIMARY KEY,
                     lat VARCHAR(255),
                     lng VARCHAR(255),
                     created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     created_by BIGINT DEFAULT NULL,
                     updated_by BIGINT DEFAULT NULL
);

CREATE TABLE company (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255),
                         catch_phrase VARCHAR(255),
                         bs VARCHAR(255),
                         created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         created_by BIGINT DEFAULT NULL,
                         updated_by BIGINT DEFAULT NULL
);

CREATE TABLE locations (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL
);

CREATE TABLE therapies (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL
);


CREATE TABLE address (
                         id BIGSERIAL PRIMARY KEY,
                         street VARCHAR(255),
                         suite VARCHAR(255),
                         city VARCHAR(255),
                         zipcode VARCHAR(255),
                         geo_id BIGINT DEFAULT NULL,
                         created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         created_by BIGINT DEFAULT NULL,
                         updated_by BIGINT DEFAULT NULL,
                         CONSTRAINT fk_geo FOREIGN KEY (geo_id) REFERENCES geo (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       address_id BIGINT DEFAULT NULL,
                       phone VARCHAR(255),
                       website VARCHAR(255),
                       company_id BIGINT DEFAULT NULL,
                       created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       roles VARCHAR(255),
                       CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE CASCADE ON UPDATE CASCADE,
                       CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES company (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE comments (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          body TEXT NOT NULL,
                          post_id BIGINT DEFAULT NULL,
                          user_id BIGINT DEFAULT NULL,
                          created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          created_by BIGINT NOT NULL,
                          updated_by BIGINT NOT NULL,
                          CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

CREATE TABLE user_role (
                           id BIGSERIAL PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           role_id BIGINT NOT NULL,
                           CONSTRAINT fk_security_user_id FOREIGN KEY (user_id) REFERENCES users (id),
                           CONSTRAINT fk_security_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);
CREATE TABLE companies (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           location VARCHAR(255) NOT NULL,
                           category VARCHAR(255),
                           description TEXT,
                           image_url VARCHAR(255)
);

-- Insert data
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
INSERT INTO companies (name, location, category, image_url) VALUES
                                ('Roosters Barbershop', 'Αμπελόκηποι, Αθήνα', 'Κουρείο', '/assets/images/homepage/roosters.jpg'),
                                ('High Lash Artistry', 'Ίλιον, Αθήνα', 'Beauty Salon', '/assets/images/homepage/highlashartistry.jpg'),
                                ('Salty Beauty Clinic', 'Εύοσμος, Θεσσαλονίκη', 'Beauty Salon', '/assets/images/homepage/saltybeautyclinic.jpg')
;



INSERT INTO locations (name) VALUES
                                 ('Αθήνα'),   -- Athens
                                 ('Θεσσαλονίκη'),  -- Thessaloniki
                                 ('Πάτρα'),  -- Patras
                                 ('Ηράκλειο'),  -- Heraklion
                                 ('Λάρισα'),  -- Larissa
                                 ('Βόλος'),  -- Volos
                                 ('Ιωάννινα'),  -- Ioannina
                                 ('Χανιά'),  -- Chania
                                 ('Καβάλα'),  -- Kavala
                                 ('Τρίκαλα')
;
INSERT INTO therapies (name) VALUES
                                 ('Κομμωτική'),
                                 ('Νύχια'),
                                 ('Φρύδια και Βλεφαρίδες'),
                                 ('Μασάζ'),
                                 ('Υπηρεσίες Κουρείου'),
                                 ('Αποτρίχωση'),
                                 ('Σώμα'),
                                 ('Τατουάζ και Piercing'),
                                 ('Μακιγιάζ'),
                                 ('Περιποίηση Προσώπου και Δέρματος');
