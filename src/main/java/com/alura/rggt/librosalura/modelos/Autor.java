package com.alura.rggt.librosalura.modelos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Autor(@JsonAlias("name") String nombre,
                    @JsonAlias("birth_year") String fechaDeNacimiento
) {
}
