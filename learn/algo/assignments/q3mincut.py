import random
from collections import defaultdict
from random import randint

class Edge:
    def __init__(u, v):
       self.u = u
       self.v = v

def graph(filename):
    from copy import copy
    lines = open(filename).read().split("\r\n")
    g = defaultdict(list)
    for line in lines:
        line = line.rstrip()
        if line == "":
            continue
        row = line.split('\t')
        u = int(row[0])
        for v in row[1:]:
           v = int(v)
           g[u].append([u,v])
    return g

def mincut(g):
    n = len(g)
    min = contract(g)
    print min
    for i in range (1, n):
       c = contract(g)
       if c < min:
           min = c
           print min
    return min

def contract(g):
    random.seed()
    from copy import deepcopy
    g = deepcopy(g)
    n = len(g)
    while n > 2:
        contract_iter(g)
        n = n - 1
    u = g.keys()[0]
    return len(g[u])

def choose_edge(g):
    edges = g.values()
    e = [item for sublist in edges for item in sublist]
    x = randint(0, len(e)-1)
    #print e[x]
    return e[x]

def contract_iter(g):
    (u, v) = choose_edge(g)
    for v_edge in g[v]:
        w = v_edge[1]
        if u != w:
            v_edge[0] = u
            g[u].append(v_edge)
            for w_edge in g[w]:
                y = w_edge[1]
                if y == v:
                    w_edge[1] = u

    g[u] = filter(lambda u_edge: u_edge[1] != v , g[u])
    g.pop(v)

def run(filename):
    g = graph(filename)
    #print g
    print "Min cut is " + str(mincut(g))

if __name__ == "__main__":
     run("./kargerMinCut.txt")
