package com.liviaaurich.locadora.service.dto.dropdown;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe Objeto de Transferência de Dados Dropdown
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DropdownDTO {

    Long value;
    String label;
}
