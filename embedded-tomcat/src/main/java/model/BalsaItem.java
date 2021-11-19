package model;

public class BalsaItem {

	private int id;
	private double korkeus;
	private double pituus;
	private double leveys;
	private double paino;
	private String grain;
	private double tiheys;

	public BalsaItem(double tiheys, double korkeus, double leveys, double paino, double pituus) {
		this.tiheys = tiheys;
		this.korkeus = korkeus;
		this.leveys = leveys;
		this.paino = paino;
		this.pituus = pituus;
	}

	public BalsaItem(int id, double tiheys, double korkeus, double pituus, double leveys, double paino, String grain) {
		this.id = id;
		this.korkeus = korkeus;
		this.pituus = pituus;
		this.leveys = leveys;
		this.paino = paino;
		this.grain = grain;
		this.tiheys = tiheys;
	}

	public BalsaItem(int id) {
		this.id = id;
	}

	public BalsaItem(double tiheys, double korkeus, double pituus, double leveys, double paino, String grain) {
		this.tiheys = tiheys;
		this.korkeus = korkeus;
		this.pituus = pituus;
		this.leveys = leveys;
		this.paino = paino;
		this.grain = grain;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getKorkeus() {
		return korkeus;
	}

	public void setKorkeus(double korkeus) {
		this.korkeus = korkeus;
	}

	public double getLeveys() {
		return leveys;
	}

	public void setLeveys(double leveys) {
		this.leveys = leveys;
	}

	public double getPaino() {
		return paino;
	}

	public void setPaino(double paino) {
		this.paino = paino;
	}

	public double getPituus() {
		return pituus;
	}

	public void setPituus(double pituus) {
		this.pituus = pituus;
	}

	public String getGrain() {
		return grain;
	}

	public void setGrain(String grain) {
		this.grain = grain;
	}

	public double getTiheys() {
		return tiheys;
	}

	public void setTiheys(double tiheys) {
		this.tiheys = tiheys;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof BalsaItem && ((BalsaItem) other).id == this.id;
	}

	@Override
	public String toString() {
		return "BalsaItem [id=" + id + ", korkeus=" + korkeus + ", pituus=" + pituus + ", leveys=" + leveys + ", paino="
				+ paino + ", grain=" + grain + ", tiheys=" + tiheys + "]";
	}
}