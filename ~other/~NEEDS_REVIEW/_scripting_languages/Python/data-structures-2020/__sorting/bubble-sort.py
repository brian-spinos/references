def bubbleSort(arr):
    n = len(arr)
    for i in range(n-1):
        noSwap = True
        for j in range(n-1-i):
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]
                noSwap = False
        if noSwap:
            return

arr = [5,4,6,3,7,2,8,1,0,9]
bubbleSort(arr)
print(arr)
# [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
