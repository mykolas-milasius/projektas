package pagrindinis.projektas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prasymas")
public class Prasymas
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	     
	    @Column(nullable = false)
	    private String asmenskodas;
	    
	    @Column(nullable = false)
	    private String vardas;
	    
	    @Column(nullable = false)
	    private String pavarde;
	    
	    @Column(nullable = false)
	    private String klase;

	    @Column(nullable = false)
	    private String pavadinimas;
	    
	    @Column(nullable = false)
	    private int patvirtintas;
	    
		public Integer getId() {
			return id;
		}

		public String getGimimoData()
		{
			String gimimo_data = null;
			String skaitmenys[] = asmenskodas.split("");
			
			if(skaitmenys[0].equals("1") || skaitmenys[0].equals("2")) // 18xx
			{
				gimimo_data = "18" + skaitmenys[1] + skaitmenys[2] + "-" + skaitmenys[3] + skaitmenys[4] + "-" + skaitmenys[5] + skaitmenys[6];
			}
			else
			{
				if(skaitmenys[0].equals("3") || skaitmenys[0].equals("4"))// 19xx
				{
					gimimo_data = "19" + skaitmenys[1] + skaitmenys[2] + "-" + skaitmenys[3] + skaitmenys[4] + "-" + skaitmenys[5] + skaitmenys[6];
				}
				else
				{
					if(skaitmenys[0].equals("5") || skaitmenys[0].equals("6")) // 20xx
					{
						gimimo_data = "20" + skaitmenys[1] + skaitmenys[2] + "-" + skaitmenys[3] + skaitmenys[4] + "-" + skaitmenys[5] + skaitmenys[6];
					}
					else
					{
						return "Asmens kodas yra neteisingas";
					}
				}
			}
			return gimimo_data;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}

		public String getAsmenskodas() {
			return asmenskodas;
		}

		public void setAsmenskodas(String asmenskodas) {
			this.asmenskodas = asmenskodas;
		}

		public String getVardas() {
			return vardas;
		}

		public void setVardas(String vardas) {
			this.vardas = vardas;
		}

		public String getPavarde() {
			return pavarde;
		}

		public void setPavarde(String pavarde) {
			this.pavarde = pavarde;
		}

		public String getKlase() {
			return klase;
		}

		public void setKlase(String klase) {
			this.klase = klase;
		}

		public String getPavadinimas() {
			return pavadinimas;
		}

		public void setPavadinimas(String pavadinimas) {
			this.pavadinimas = pavadinimas;
		}

		public int getPatvirtintas() {
			return patvirtintas;
		}

		public void setPatvirtintas(int patvirtintas) {
			this.patvirtintas = patvirtintas;
		}
}
