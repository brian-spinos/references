def insertionSort(arr):
    if len(arr) < 1: return
    for i in range(1,len(arr)):
        tempVal = arr[i]
        flag = True
        for j in range(i):
            idx = i-1-j
            if arr[idx] > tempVal:
                arr[idx+1] = arr[idx] # move to the right
            else:
                arr[idx+1] = tempVal
                flag = False
                break
        if flag:
            arr[0] = tempVal

if __name__ == "__main__":
    arr = [5,4,6,3,7,2,8,1,0,9]
    insertionSort(arr)
    print(arr)
    # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
