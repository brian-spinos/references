#------------------------------ Selection sort

def selectionSort(arr):
    for i in range(len(arr)-1):
        minIdx = i
        for j in range(i, len(arr)):
            if arr[j] < arr[minIdx]:
                minIdx = j
        arr[i], arr[minIdx] = arr[minIdx], arr[i]

if __name__ == "__main__":
    arr = [5,4,6,3,7,2,8,1,0,9]
    selectionSort(arr)
    print(arr)
    # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
