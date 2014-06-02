from collections import defaultdict
from random import randint

def d():
    return defaultdict(int) 

def graph(filename):
    from copy import copy
    lines = open(filename).read().split("\n")
    g = defaultdict(d)
    for line in lines:
        line = line.rstrip()
        row = line.split('\t')
        u = row[0]
        if u != "":
            for v in row[1:]:
               g[u][v] += 1
    return g

def run(filename):
    g = graph(filename)
    mincut(g)
 
def mincut(g):
    n = len(g)
    min = contract(g)
    print min
    for i in range (1, n*n):
       c = contract(g)
       if c < min:
           #print g
           min = c
           print min
    return min

def contract(g):
    from copy import deepcopy
    n = len(g)
    g = deepcopy(g)
    while n > 2:
        contract_iter(g) 
        n = n - 1
    edge_count = 0
    u = g.values()[0]
    for v in u.keys():
        edge_count += u[v]
    return edge_count 

def choose_edge(g):
    u = g.keys()[randint(0, len(g)-1)]
    v = g[u].keys()[randint(0, len(g[u])-1)]
    return (u,v)

def contract_iter(g):
    (u,v) = choose_edge(g)
    c1 = g[u][v]
    c2 = g[v][u]
    for s in g[v].keys():
       if u != s:
           g[u][s] += c1
           g[s][u] += c2
           del g[s][v]
    del g[v]
    del g[u][v]

if __name__ == "__main__":
    run("./kargerMinCut.txt")

