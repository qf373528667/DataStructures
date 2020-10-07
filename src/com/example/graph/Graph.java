package com.example.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

	private ArrayList<String> vertexList; //存储顶点集合
	private int[][] edges; //存储图对应的邻接矩阵
	private int numOfEdges;//表示边的数目
	//定义一个数组boolean[]，记录某个结点是否被访问
	private boolean[] isVisited;
	public static void main(String[] args) {

		//测试一把图是否创建ok
		int n=8;//结点的个数
		//String[] vertexValue= {"A","B","C","D","E"};
		String[] vertexValue= {"1","2","3","4","5","6","7","8"};
		//创建图对象
		Graph graph = new Graph(n);
		//循环的添加顶点
		for(String value:vertexValue) {
			graph.insertVertex(value);
		}
		//添加边
		//A-B  A-C B-C B-D B-E
//		graph.insertEdge(0,1,1); //A-B
//		graph.insertEdge(0,2,1); //A-C
//		graph.insertEdge(1,2,1); //B-C
//		graph.insertEdge(1,3,1); //B-D
//		graph.insertEdge(1,4,1); //B-E
		
		//更新边的关系
		graph.insertEdge(0,1,1);
		graph.insertEdge(0,2,1);
		graph.insertEdge(1,3,1);
		graph.insertEdge(1,4,1);
		graph.insertEdge(3,7,1);
		graph.insertEdge(4,7,1);
		graph.insertEdge(2,5,1);
		graph.insertEdge(2,6,1);
		graph.insertEdge(5,6,1);
		
		//显示一把邻结矩阵
		graph.showGraph();
		
		//测试一把，我们的dfs遍历是否ok
		System.out.println("深度遍历");  
		graph.dfs();              //A->B->C->D->E->  1->2->4->8->5->3->6->7->
		
		System.out.println();
		System.out.println("广度优先！");
		graph.bfs();              //A=>B=>C=>D=>E=>  1=>2=>3=>4=>5=>6=>7=>8=>
	}

	public Graph(int n) {
		//初始化矩阵和vertexList
		edges=new int[n][n];
		vertexList=new ArrayList<String>(n);
		numOfEdges=0;
		isVisited=new boolean[n];
	}

	//得到第一个邻结点的下标w
	public int getFirstNeighbor(int index) {
		for(int j=0;j<vertexList.size();j++) {
			if(edges[index][j]>0) {
				return j;
			}
		}
		return -1;
	}
	//根据前一个邻接结点的下标来获取下一个邻接结点
	public int getNextNeighbor(int v1,int v2) {
		for(int j=v2+1;j<vertexList.size();j++) {
			if(edges[v1][j]>0) {
				return j;
			}
		}
		return -1;
	}
	
	//深度优先遍历算法
	//i 第一次就是0
	private void dfs(boolean[] isVisited,int i) {
		//首先访问该节点，输出
		System.out.print(getValueByIndex(i)+"->");
		//将结点设置为已经访问
		isVisited[i]=true;
		//得到i第一个邻结点的下标w
		int w=getFirstNeighbor(i);
		while(w!=-1) {  //说明有邻接结点
			if(!isVisited[w]) {
				dfs(isVisited,w);
			}
			//如果w结点已经被访问过，就应该查找
			w=getNextNeighbor(i, w);
		}
	}
	
	//对dfs 进行一个重载，遍历所有的结点，并进行dfs
	public void dfs() {
		for(int i=0;i<isVisited.length;i++) {
			isVisited[i]=false;
		}
		//遍历所有的结点，进行dfs
		for(int i=0;i<getNumOfVertex();i++) {
			if(!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}
	
	//对一个结点进行广度优先遍历的方法
	private void bfs(boolean[] isVisited,int i) {
		int u;//表示队列的头结点对应下标
		int w;//表示邻接结点下标w
		//队列，记录结点访问的顺序
		LinkedList queue = new LinkedList();
		//访问结点，输出结点信息
		System.out.print(getValueByIndex(i)+"=>");
		//标记为已访问
		isVisited[i]=true;
		//将结点加入队列
		queue.addLast(i);
		while(!queue.isEmpty()) {
			//取出队列的头结点下标
			u = (Integer)queue.removeFirst();
			//得到第一个邻接结点的下标
			w = getFirstNeighbor(u);
			while(w!=-1) { //找到
				//是否访问过
				if(!isVisited[w]) {
					System.out.print(getValueByIndex(w)+"=>");
					//标记已经访问过
					isVisited[w]=true;
					//入队
					queue.addLast(w);
				}
				//以u为前驱结点，找w后面的下一个邻结点
				w=getNextNeighbor(u, w); //体现出我们的广度优先
			}
		}
	}
	//遍历所有的结点，都进行广度优先搜索
	public void bfs() {
		for(int i=0;i<isVisited.length;i++) {
			isVisited[i]=false;
		}
		for(int i=0;i<getNumOfVertex();i++) {
			if(!isVisited[i]) {
				bfs(isVisited,i);
			}
		}
	}
	
	
	//图中常用的方法
	//返回结点的个数
	public int getNumOfVertex() {
		return vertexList.size();
	}
	//显示图对应的矩阵
	public void showGraph() {
		for(int[] link:edges) {
			System.out.println(Arrays.toString(link));
		}
	
	}
	//得到边的数目
	public int getNumOfEdges() {
		return numOfEdges;
	}
	//返回结点i（下标）对应的数据 0->"A" 1->"B" 2->"C"
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}
	//返回v1和v2的权值
	public int getWeight(int v1,int v2) {
		return edges[v1][v2];
	}
	
	//插入结点
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}
	//添加边
	/**
	 * 
	 * @param v1            表示点的下标 即第几个顶点  "A"-"B" "A"->0 "B"->1
	 * @param v2            表示第二个顶点的下标
	 * @param weight
	 */
	public void insertEdge(int v1,int v2,int weight) {
		edges[v1][v2]=weight;
		edges[v2][v1]=weight;
		numOfEdges++;
	}
	
}
