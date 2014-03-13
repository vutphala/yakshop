package com.vutphala.yakshop.modal;



public class LabYakInstance implements YakInstance {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3743592853386583855L;
	private Yak yak=null;
	int instanceDay=-1;
	
	public LabYakInstance(Yak yak, int instanceDay){
		if(yak==null){
			throw new UnsupportedOperationException("You can not Create Yak Instance with out yak");
		}
		this.instanceDay=instanceDay;
		this.yak=yak;
		
	}
	@Override
	public double getMilk() {
			int availableMilk = 0;
			for (int i = yak.getLastMilkConsumedAge(); i < (yak.getAge() + instanceDay); i++) {
				availableMilk = availableMilk + getMilkProducedByDay(i);
			}
			System.out.println(availableMilk);
		return (double)availableMilk / 100;

	}

	private int getMilkProducedByDay(int day) {
		return (50 * 100) - (day * 3);
	}

	@Override
	public double getAge() {
		return (double)(yak.getAge()+instanceDay)/100;
	}

	@Override
	public int getWool() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return yak.getName();
	}
	

	public int getStatus() {
		
		if ((yak.getAge()+instanceDay)>= (10 * 100)) {
			return Yak.DIED;
		}
		return Yak.ALIVE;
	}
	@Override
	public double getAgeLastShaved(){
		return (double)yak.getLastShaveAge()/100;
	}

}
