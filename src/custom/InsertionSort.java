package custom;

public class InsertionSort {

	public static void sort(int[] data){
		int currentInd;
		for(int pos = 1; pos < data.length; ++pos){
			currentInd = pos;
			
			while(currentInd > 0 && data[currentInd] < data[currentInd - 1]){
				swap(data, currentInd , currentInd-1);
				currentInd = currentInd - 1;
			}
		}
	}

	public static void swap(int[] data, int i, int j){
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	public static void main(String[] args){
		
		int[] data = {7,16,66,43,97,51};
		SelectionSort.sort(data);
		for(int i=0; i<data.length; ++i)
			System.out.println(data[i]);
	}
}
