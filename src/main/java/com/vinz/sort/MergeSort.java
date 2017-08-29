package com.vinz.sort;

import java.util.Arrays;

public class MergeSort {

	private int size;
	private int[] arrAux;
	private int[] arrInput;

	public MergeSort(int[] arrInput) {
		this.arrInput = arrInput;
		size = arrInput.length;
		arrAux = new int[size];
	}

	public int[] mergeSorting() {
		sort(0, size - 1);
		return arrInput;
	}

	public void sort(int low, int high) {
		if (low < high) {
			int mid = low + ((high - low)) / 2;
			sort(low, mid);
			sort(mid + 1, high);
			merge(low, mid, high);
		}
	}

	public void merge(int low, int mid, int high) {
		for (int i = low; i <= high; i++) {
			arrAux[i] = arrInput[i];
		}
		int i = low;
		int j = mid + 1;
		int k = low;

		while (i <= mid && j <= high) {
			if (arrAux[i] <= arrAux[j]) {
				arrInput[k] = arrAux[i];
				i++;
			} else {
				arrInput[k] = arrAux[j];
				j++;
			}
			k++;
		}
		while (i <= mid) {
			arrInput[k] = arrAux[i];
			i++;
			k++;
		}
	}

	public void displayArray(int[] b) {
		String result = Arrays.stream(b).mapToObj(String::valueOf).reduce(" ", (i, j) -> i + " " + j);
		System.out.println(result);
	}

	public static void main(String[] args) {
		int[] a = { 2, 1, 6, 3, 9, 4, 5, 10, 2, 123, 21, 32, 43, 12, 22, 11 };
		MergeSort m = new MergeSort(a);
		int[] b = m.mergeSorting();
		m.displayArray(b);

	}
}
