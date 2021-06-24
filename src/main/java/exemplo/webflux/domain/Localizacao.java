package exemplo.webflux.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("localizacao")
public class Localizacao {

	@Id
	private long id;
	@NotNull(message = "Longitude esta null")
	private String longitude;
	@NotNull(message = "Latitude esta null")
	private String latitude; 
	
}
