
def r_count_inversions(a):
	if len(a) <= 1:
		return (a,0)
	else:
		mid = len(a) / 2
		(r,u) = r_count_inversions(a[:mid])
		(s,v) = r_count_inversions(a[mid:])
		size_r = len(r)
		size_s = len(s)
		i = 0
		j = 0
		t = []
		count = 0
		while (i < size_r) and (j < size_s):
			if r[i] <= s[j]:
				t.append(r[i])
				i = i + 1
			else:
				count += size_r - i
				t.append(s[j])
				j = j + 1
		while i < size_r:
			t.append(r[i])
			i = i + 1
		while j < size_s:
			t.append(s[j])
			j = j + 1
		#print (t, u+v+count)
		return (t, u+v+count)
				
def count_inversions(exp, a):
	result = r_count_inversions(a)
	sorted_a = list(a)
	sorted_a.sort()
	print exp == result[1], sorted_a == result[0], exp, result[1]


count_inversions(0, [])
count_inversions(0, [2])
count_inversions(1, [2,1])
count_inversions(0, [1,2,3])
count_inversions(2, [2,3,1])
count_inversions(3, [3,2,1])
count_inversions(3, [3,2,1,4])
count_inversions(6, [4,3,2,1])
count_inversions(10, [5,4,3,2,1])

#x=count_inversions(l)
#print len(x[0])
#print x
#print x[0][len(x[0])-1]

l=open("/Users/admin/Downloads/IntegerArray.txt", "r").read().split()
nums=[int(k) for k in l]
count_inversions(2407905288, nums)

