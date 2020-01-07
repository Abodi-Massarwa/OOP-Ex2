Ex2
In this task we dealt with graphs that is a set of vertices and edges.
We implemented Dgraph interface and graph algo interface that implements graph_algorithims.
And we draw the graph by stdDraw.
We encountered some problem in the task that hindered us in the last hours from submitting the assignment and we had to deal with them.
My team mate abodi noticed that in get E() in the Dgraph class that we had to use hashmap of hashmaps in the edges, because we had to return collection of edges in O(1) time which we couldn’t achieved with our implementation since we used the key as a string,
For instance the key of each edge looks like (src,dest)
Src is the key of node data and dest is the same.

wiki : 
I want to explain about task in short:
We used hashmap data structure because the search time complexity O(1) and we needed a special key for every vertex
We helped with Dijkstra algorithm that was in the Moodle in order to count shortest path distance between two vertices.
In function that checked if the graph is connected we used Boolean array to check if we visited the vertex as It was an important part in our recursive algorithm , we implemented an algorithm to count the strongly-connected components(by visiting the neighbours of each vertex and flagging them as true) and at the moment we had more than one component we got out of the algorithm and returned false ,otherwise we returned true.
In the section of Graphical User Interface we created a class called Graphs. that implements collection <DGraph> interface , in other words , Graphs is a collection of Dgraph objects , we used ArrayList in order to implement the unimplemented methods of collection . the we used StdDraw library in order to visualize the graph in GUI in fact we can visualize a collection of graphs at once. We used caller inner class which extends the JFrame class a inserted a progress bar in the windows to make it look like it’s loading 😊


