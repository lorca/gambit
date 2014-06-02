import random
from collections import defaultdict

def graph(filename):
    from copy import copy
    lines = open(filename).read().split("\n")
    edges = defaultdict(int)
    n = 0
    for line in lines:
        line = line.rstrip()
        row = line.split(" ")
        u = row[0]
        if u != "":
            n = n + 1
            for v in row[1:]:
               edges[frozenset((u,v))] += 1
    return (n, edges)

def run(filename):
    (n,g) = graph(filename)
    mincut(g, n)
    print g
 
def mincut(g, n):
    min = contract(g, n)
    print min
    for i in range (1, n*n):
       c = contract(g, n)
       if c < min:
           min = c
           print min
    return min

def contract(g, n):
    from copy import deepcopy
    g = deepcopy(g)
    while n > 2:
        contract_iter(g) 
        n = n - 1
    edge_count = g.values()[0]
    return edge_count 

def choose_edge(g):
    (u, v) = random.sample(g.keys(), 1)[0]
    return (u,v)

def contract_iter(g):
    (u, v) = choose_edge(g)
    toRemove = []
    toAdd = defaultdict(int)
    for edge in g:
       (r,s) = edge
       if r == v:
           c = g[edge]
           toRemove.append(edge)
           if u != s:
               toAdd[frozenset((u, s))] += c
       elif s == v:
           c = g[edge]
           toRemove.append(edge)
           if u != r:
               toAdd[frozenset((u, r))] += c
    for key in toRemove:
       del g[key]
    for key in toAdd.keys():
       g[key] += toAdd[key] 

if __name__ == "__main__":
    run("./k.txt")

