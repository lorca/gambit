
def quicksort(a, l, r, pivot):
    comp = 0
    if l < r - 1:
        #swap(a, l, r - 1)
        pivot(a, l, r)
        p = partition(a, l, r)
        comp += (r - l) - 1
        comp += quicksort(a, l, p, pivot)
        comp += quicksort(a, p + 1, r, pivot)
    return comp

def left_pivot(a, l, r):
    pass

def right_pivot(a, l, r):
    swap(a, l, r - 1)
    
def median_of_three_pivot(a, l, r):
    m = l + ((r - 1 - l) / 2)
    t = [(a[l], 0, l), (a[m], 1, m), (a[r - 1], 2, r - 1)]
    quicksort(t, 0, 3, left_pivot)
    p = t[1][2]
    swap(a, l, p)

def rand_pivot(a, l, r):
    from random import randint
    swap(a, l, randint(l, r - 1))

def partition(a, l, r):
    p = a[l]
    i = l + 1
    for j in range(l + 1, r):
        if a[j] < p:
            swap(a, i, j)
            i = i + 1
    swap(a, l, i - 1)
    return i - 1

def swap(a, i, j):
    temp = a[i]
    a[i] = a[j]
    a[j] = temp

def run_assignment(filename):
    from copy import copy
    ints = [int(i) for i in open(filename).read().split()]

    l_ints = copy(ints)
    r_ints = copy(ints)
    m_ints = copy(ints)
    x_ints = copy(ints)

    l_ans = quicksort(l_ints, 0, len(l_ints), left_pivot)
    r_ans = quicksort(r_ints, 0, len(r_ints), right_pivot)
    m_ans = quicksort(m_ints, 0, len(m_ints), median_of_three_pivot)
    x_ans = quicksort(x_ints, 0, len(x_ints), rand_pivot)
    
    sorted_ints = copy(ints)
    sorted_ints.sort()
    assert l_ints == sorted_ints
    assert r_ints == sorted_ints
    assert m_ints == sorted_ints
    assert x_ints == sorted_ints
    
    return (filename,l_ans,r_ans,m_ans,x_ans)

if __name__ == "__main__":
    print run_assignment("/Users/admin/Downloads/10.txt")
    print run_assignment("/Users/admin/Downloads/100.txt")
    print run_assignment("/Users/admin/Downloads/1000.txt")
    # print run_assignment("/Users/admin/Downloads/IntegerArray.txt")
    print run_assignment("/Users/admin/Downloads/QuickSort.txt")




