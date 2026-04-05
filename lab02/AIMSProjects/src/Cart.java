public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private int qtyOrdered = -1; 
    private DigitalVideoDisc itemOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (this.qtyOrdered >= MAX_NUMBERS_ORDERED - 1) { 
            System.out.println("The cart is full");
        } else {
            this.itemOrdered[++this.qtyOrdered] = disc;
            System.out.println("The disc has been added");
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        if (this.qtyOrdered == -1) {
            System.out.println("The cart is empty");
            return;
        }
        
        int index = -1;
        for (int i = 0; i <= this.qtyOrdered; i++) {
            if (this.itemOrdered[i] != null && this.itemOrdered[i].getTitle().equals(disc.getTitle())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < this.qtyOrdered; i++) {
                this.itemOrdered[i] = this.itemOrdered[i + 1];
            }
            this.itemOrdered[this.qtyOrdered] = null; 
            this.qtyOrdered--;
            System.out.println("The disc has been removed.");
        } else {
            System.out.println("The disc is not in the cart.");
        }
    }
    public double totalCost() {
    	double result=0;
    	if(this.qtyOrdered==-1) {
    		return 0;
    	}
    	else {
    		for(int i=0;i<=this.qtyOrdered;i++) {
    			result+=this.itemOrdered[i].getCost();
    		}
    		return result;
    	}  	  	
    }
    public void printAllInformation() {
    	if(this.qtyOrdered==-1) {
    		return;
    	}else {
    		for(int i=0;i<=this.qtyOrdered;i++) {
    			System.out.println((i+1)+"     "+(this.itemOrdered[i].getTitle())+"          "+(this.itemOrdered[i].getCost()));
    		}
    		System.out.println("      "+"Total"+"          "+this.totalCost());
    	}
    }
}
