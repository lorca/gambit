import random
from random import randint

def graph(filename):
    from copy import copy
    lines = open(filename).read().split("\r\n")
    nodes = {}
    edges = {}
    for line in lines:
#        line = line.rstrip()
        if line == "":
            continue
        row = line.split('\t')
        row = row[:len(row)-1]
#        print line
        print row
        v = row[0]
        nodes[v] = set(row[1:])
        print len(nodes[v])
        for u in row[1:]:
           add(edges, (v,u), 1)
    return (nodes, edges) 

def add(edges, key, c):
    if not edges.has_key(key):
        edges[key] = c 
    else:
        edges[key] += c

def run(filename):
    g = graph(filename)
    #print g
    print "Min cut is " + str(mincut(g))
 
def mincut(g):
    n = len(g[0])
    min = contract(g)
    print min
    for i in range (1, n*n*n):
       c = contract(g)
       if c < min:
           min = c
           print min
    return min

def contract(g):
    random.seed()
    from copy import deepcopy
    g = deepcopy(g)
    n = len(g[0])
    while n > 2:
        contract_iter(g) 
        n = n - 1
    edge_count = g[1].values()[0]
    return edge_count 

def choose_edge(g):
    l = list(g[1])
    x = randint(0, len(l)-1)
    return l[x]
    #(u, v) = random.sample(g[1], 1)[0]
    return (u,v)

def contract_iter(g):
    (u, v) = choose_edge(g)
    v_adj = g[0].pop(v)
    for v2 in v_adj:
       # remove edges (v - v2)
       c1 = g[1].pop((v,v2))
       c2 = g[1].pop((v2,v))

       # remove v from v2's adjacency list
       g[0][v2].remove(v)

       # don't add self-loops  
       if v2 != u:
           # add v2 as an edge to/from u
           g[0][u].add(v2)
           g[0][v2].add(u)

           # add edges (u , v2)
           add(g[1], (u,v2), c1)
           add(g[1], (v2,u), c2)

if __name__ == "__main__":
     run("./kargerMinCut.txt")
#    run("./k.txt")
#    run("./k2.txt")
#    run("./k3.txt")
#    run("./k4.txt")
#    run("./k5.txt")
#    run("./k6.txt")

