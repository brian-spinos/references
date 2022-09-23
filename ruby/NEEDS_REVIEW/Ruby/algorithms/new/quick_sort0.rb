class Quicksort
    
    def sort(array)
        quicksort(array, 0, array.size - 1)
    end

    def quicksort(array, left, right)
        if(left >= right)
            return
        end

        pivot = array[(left + right) / 2]
        index = partition(array, left, right, pivot)
        quicksort(array, left, index - 1)
        quicksort(array, index, right)
    end

    def partition(array, left, right, pivot)
        while(left <= right)
            while(array[left] < pivot)
                left += 1
            end

            while(array[right] > pivot)
                right -= 1
            end

            if(left <= right)
                # swap
                temp = array[left]
                array[left] = array[right]
                array[right] = temp

                left += 1
                right -= 1
            end

        end

        return left
    end
end


array = [0,1,9,2,8,3,7,4,6,5]
q = Quicksort.new()
q.sort(array)
puts array # [0,1,2,3,4,5,6,7,8,9]
