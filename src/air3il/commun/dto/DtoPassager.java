package air3il.commun.dto;

public class DtoPassager {
	
	
	// Champs
	
	private int			id;
	
	private String			nom;
        
        private String			prenom;
	
	private String			tel;
	
	private String			email;
	
	
//methodes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	

	// Constructeurs
	
	public DtoPassager() {
	}

	public DtoPassager(int id, String nom, String prenom ,String tel ,String email ) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
                this.tel = tel;
                this.email = email;
	}

	
	
	// hashcode() + equals()
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtoPassager other = (DtoPassager) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
