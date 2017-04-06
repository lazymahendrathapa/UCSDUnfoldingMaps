package custom;

public class SelectionSort {

	public static void sort(int[] data){
	
		int indexMin;
		
		for(int i=0; i<data.length-1; ++i){
	
			indexMin = i;
			
			for(int j=i+1; j<data.length; ++j){
				
				if(data[indexMin]>data[j]){
					indexMin = j;
				}
			}
			
			int temp = data[i];
			data[i] = data[indexMin];
			data[indexMin] = temp;
		}
	}
	
	
	public static void main(String[] args){
		
		int[] data = {7,16,66,43,97,51};
		SelectionSort.sort(data);
		for(int i=0; i<data.length; ++i)
			System.out.println(data[i]);
	}
}
