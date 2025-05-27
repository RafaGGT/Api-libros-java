package com.alura.rggt.librosalura.modelos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Libros( @JsonAlias("title") String titulo,
                      @JsonAlias("authors") List<Autor> autor,
                      @JsonAlias("languages") List<String> idiomas,
                      @JsonAlias("download_count") Double numeroDeDescargas) {
}


