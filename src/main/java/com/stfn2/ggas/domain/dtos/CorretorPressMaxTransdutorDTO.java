package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.CorretorPressMaxTransdutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorretorPressMaxTransdutorDTO extends BaseDTO {
        private Long id;

    	public CorretorPressMaxTransdutorDTO (CorretorPressMaxTransdutor entity) {super(); 	}

}