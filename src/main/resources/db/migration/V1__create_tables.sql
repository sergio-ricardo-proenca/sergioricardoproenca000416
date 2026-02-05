CREATE TABLE artistas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    genero VARCHAR(200) -- Ajustado para bater com sua classe Java
);

CREATE TABLE albuns (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL, -- Ajustado para bater com sua classe Java
    capa_url VARCHAR(500)
);

CREATE TABLE artista_album (
    artista_id INT NOT NULL,
    album_id INT NOT NULL,
    PRIMARY KEY (artista_id, album_id),
    CONSTRAINT fk_artista FOREIGN KEY (artista_id) REFERENCES artistas(id),
    CONSTRAINT fk_album FOREIGN KEY (album_id) REFERENCES albuns(id)
);

CREATE TABLE regionais (
    id INT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

-- Carga inicial exigida no edital
INSERT INTO artistas (nome, genero) VALUES ('Serj Tankian', 'Rock');
INSERT INTO artistas (nome, genero) VALUES ('Guns N’ Roses', 'Hard Rock');