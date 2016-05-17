package air3il.commun.dto;


public class DtoContact {

	
	// Champs

	private int				id;
    
    private DtoReservation     reservation;

	private String			tel;

	private String			email;


	// Propriétés

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public DtoReservation getReservation() {
        return reservation;
    }

    public void setReservation(DtoReservation reservation) {
        this.reservation = reservation;
    }
    
    public String getTel() {
		return tel;
	}

	public void setLibelle(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	// Constructeurs
	
	public DtoContact() {
	}
	
	public DtoContact(int id, DtoReservation reservation, String tel, String email) {
		this.id = id;
        this.reservation = reservation;
		this.tel = tel;
		this.email = email;
	}
	
	
	
	// hashcode() et equals()
	
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
		DtoContact other = (DtoContact) obj;
		if (id != other.id)
			return false;
		return true;
	}

    public void setTel(String tel) {
this.tel = tel;
    }
	
}
