import greenfoot.*;
import java.util.ArrayList;

/**
 * This class declares, instantiates, and initializes a new deck.  
 * Students should implement the sort method at the bottom of this class 
 * to see if they understand basic ArrayList sort algorithms.
 * 
 * @author LL
 * @version 2025.2.30
 */

public class Dealer extends Actor
{
    private Deck deck;
    private Card[] shuffledDeck;
    private Card[] selectionSort;
    private Card[] insertionSort;
    private Card[] mergeSort;
    
    // Instantiates a new deck
    public Dealer()
    {
        deck = new Deck();
        shuffledDeck = deck.getShuffledDeck();
        initializeSortArrays();
    }
    
    // Adds cards to board after Deck is initialized
    public void addedToWorld(World world)
    {
        showCards(world, shuffledDeck, 80);
        showCards(world, selectionSort(selectionSort, shuffledDeck.length), 220);
        showCards(world, insertionSort(insertionSort, shuffledDeck.length), 360);
        showCards(world, mergeSort(mergeSort, shuffledDeck.length), 500);
    }    
    
    // Sets all the sorted arrays equal to the shuffledDeck array
    private void initializeSortArrays()
    {
        selectionSort = new Card[shuffledDeck.length];
        insertionSort = new Card[shuffledDeck.length];
        mergeSort = new Card[shuffledDeck.length];
        
        for(int i = 0; i < shuffledDeck.length; i++)
        { 
            Card card1 = new Card(shuffledDeck[i].getImageFile(), shuffledDeck[i].getValue());
            Card card2 = new Card(shuffledDeck[i].getImageFile(), shuffledDeck[i].getValue());
            Card card3 = new Card(shuffledDeck[i].getImageFile(), shuffledDeck[i].getValue());
            selectionSort[i] = card1;
            insertionSort[i] = card2;
            mergeSort[i] = card3;
        }
    }
    
    // Places card on board at y position
    private void showCards(World world, Card[] cards, int y)
    {
       for(int i = 0; i < cards.length; i++)
       {
           world.addObject(cards[i], 58 + 30 * i, y);
       } 
    }
    
    /* 
     * Selection Sort Pseudocode:
     * - Iterate through the list from index 0 to n-1.
     * - Find the smallest value in the unsorted portion.
     * - Swap it with the first element of the unsorted portion.
     * - Repeat until the array is fully sorted.
     */
    private Card[] selectionSort(Card[] arr, int n)
    {
        for (int i = 0; i < n - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
            {
                if (arr[j].getValue() < arr[minIndex].getValue())
                {
                    minIndex = j;
                }
            }
            // Swap the found minimum with the first element
            Card temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }
    
    /* 
     * Insertion Sort Pseudocode:
     * - Iterate through the list from index 1 to n-1.
     * - Pick the current element and compare it with the previous elements.
     * - Shift larger elements to the right.
     * - Insert the current element into its correct position.
     */
    private Card[] insertionSort(Card[] arr, int n)
    {
        for (int i = 1; i < n; i++)
        {
            Card key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j].getValue() > key.getValue())
            {
                arr[j + 1] = arr[j]; // Shift elements
                j--;
            }
            arr[j + 1] = key; // Place key in correct position
        }
        return arr;
    }
    
    /* 
     * Merge Sort Pseudocode:
     * - If the array has 1 or 0 elements, return it (already sorted).
     * - Split the array into two halves.
     * - Recursively sort each half.
     * - Merge the two sorted halves into a single sorted array.
     */
    private Card[] mergeSort(Card[] arr, int n)
    {
        if (n <= 1) return arr;

        int mid = n / 2;
        Card[] left = new Card[mid];
        Card[] right = new Card[n - mid];

        // Copy elements to left and right arrays
        for (int i = 0; i < mid; i++) left[i] = arr[i];
        for (int i = mid; i < n; i++) right[i - mid] = arr[i];

        // Recursively sort both halves
        left = mergeSort(left, mid);
        right = mergeSort(right, n - mid);

        // Merge sorted halves
        return merge(left, right);
    }
    
    // Merges two sorted arrays into one sorted array
    private Card[] merge(Card[] left, Card[] right)
    {
        int leftSize = left.length, rightSize = right.length;
        Card[] merged = new Card[leftSize + rightSize];
        int i = 0, j = 0, k = 0;

        // Merge elements by comparing values
        while (i < leftSize && j < rightSize)
        {
            if (left[i].getValue() <= right[j].getValue())
            {
                merged[k++] = left[i++];
            }
            else
            {
                merged[k++] = right[j++];
            }
        }

        // Copy remaining elements if any
        while (i < leftSize) merged[k++] = left[i++];
        while (j < rightSize) merged[k++] = right[j++];

        return merged;
    }
}