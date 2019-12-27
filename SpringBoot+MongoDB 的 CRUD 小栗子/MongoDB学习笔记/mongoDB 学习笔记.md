### 1. mongoDB 帮助手册

网址：https://docs.mongodb.com/manual/tutorial/getting-started/<br/>此篇文章就是基于这个网址下的学习手册作出的。

Windows MongoDB 环境安装步骤：<br/>网址：https://www.cnblogs.com/fuwentao/p/11719001.html

### 2. mongoDB 的基本使用及相关命令

- 2.1 查看数据库：db；

- 2.2 转换数据库：use &#60;dbName&#62;（在转换之前不需要创建数据库，因为当我们第一次把数据存储在某个数据库/集合中时，mongoDB 会帮我们自动创建数据库/集合）；

- 2.3 集合（Collection）：跟关系型数据库中的表（table）类似；

- 2.4 向集合中插入数据：（**db.inventory.insertMany()**：集合名为 inventory）

  ```
  db.inventory.insertMany([
     { item: "journal", qty: 25, status: "A", size: { h: 14, w: 21, uom: "cm" }, tags: [ "blank", "red" ] },
     { item: "notebook", qty: 50, status: "A", size: { h: 8.5, w: 11, uom: "in" }, tags: [ "red", "blank" ] },
     { item: "paper", qty: 10, status: "D", size: { h: 8.5, w: 11, uom: "in" }, tags: [ "red", "blank", "plain" ] },
     { item: "planner", qty: 0, status: "D", size: { h: 22.85, w: 30, uom: "cm" }, tags: [ "blank", "red" ] },
     { item: "postcard", qty: 45, status: "A", size: { h: 10, w: 15.25, uom: "cm" }, tags: [ "blue" ] }
  ]);
  
  // MongoDB adds an _id field with an ObjectId value if the field is not present in the document
  ```

  返回：（The operation returns a document that contains the acknowledgement indicator（确认指示器） and an array that contains the _id of each successfully inserted documents.）

  ![insertCollectionReturn](shortcut\insertCollectionReturn.png)
  
- 2.5 查询集合：**db.inventory.find({})**

- 2.6 将查询结果按一定的格式输出：**db.inventory.find({}).pretty()**

- 2.7 加上过滤条件查询集合：

  db.inventory.find( { status: "D" } ); // 查询 status 字段等于 D 的集合

  db.inventory.find( { qty: 0 } ); // 查询 qty 字段等于 0 的集合

  db.inventory.find( { qty: 0, status: "D" } );

  db.inventory.find( { "size.uom": "in" } ) // 嵌套在 size 中的 uom 字段等于 in 的

  db.inventory.find( { tags: "red" } ) // tags 数组中包含 red 的

  db.inventory.find( { size: { h: 14, w: 21, uom: "cm" } } ) // size 字段等于某个文档的

  db.inventory.find( { tags: [ "red", "blank" ] } )

  ![queryFilter](shortcut\queryFilter.png)

- 2.8 只返回指定的字段集合：

  db.inventory.find( {}, { item: 1, status: 1 } ); // 只返回 item 和 status 字段，但是在默认情况下还会多返回一个 _id 字段

  db.inventory.find( {}, { _id: 0, item: 1, status: 1 } ); // 将 _id 字段从返回的集合中排除（只需将其指定为 0 即可）

  ![specifyField](shortcut\specifyField.png)

### 3. Aggregation 的使用

#### 3.1 Aggregation Pipeline

直接看例子：

```
db.orders.aggregate([
   { $match: { status: "A" } },
   { $group: { _id: "$cust_id", total: { $sum: "$amount" } } }
])
```

从它的写法上我们可以看出有两个阶段（stage）：$match 阶段和 $group 阶段。

$match 阶段帮我们**过滤出集合中 status 等于 A 的文档**（documents），$group 阶段会帮我们对 $match 阶段过滤出的结果**按照 cust_id 分组**，并且多个相同 cust_id 下的 amount 会累加求和，返回最终结果。如下图：

![pipeline](shortcut\pipeline.png)

从中可以看出聚合管道的基本功能就包括**过滤查询结果**和**转换文档的输出格式**。

#### 3.2 Map-Reduce（映射-规约模式）

从名字就能看出 Map-Reduce 也分两个阶段，map 阶段用于**发表（emit）一个或多个对象**，文字描述不太好理解，看下面的图会好点；reduce 阶段就是**把 map 阶段输出的结果联合起来**，同样也是看图会好理解一点。好了，看图：

![mapReduce](shortcut\mapReduce.png)

可以发现，map-reduce 跟其他的聚合操作一样，也**可以指定查询条件和对结果进行分类**。比较特殊的是，map 和 reduce 操作用的是自定义的 JavaScript 函数。跟聚合管道相比，map-reduce 更加灵活，但是比较低效，也比较复杂。

***关于 Map-Reduce：***

Map-Reduce 是一种编程模型，在 Hadoop 中也有用到，下面用一个小故事来解释：

```
我：假设你想用薄荷、洋葱、番茄、辣椒、大蒜弄一瓶混合辣椒酱。你会怎么做呢？
妻子： 我会取薄荷叶一撮，洋葱一个，番茄一个，辣椒一根，大蒜一根，切碎后加入适量的盐和水，再放入混合研磨机里研磨，这样你就可以得到一瓶混合辣椒酱了。
我： 没错，让我们把 Map-Reduce 的概念应用到食谱上。Map和Reduce其实是两种操作，我来给你详细讲解下。

Map（映射）: 把洋葱、番茄、辣椒和大蒜切碎，是各自作用在这些物体上的一个 Map 操作。所以你给 Map 一个洋葱，Map 就会把洋葱切碎。同样的，你把辣椒，大蒜和番茄一一地拿给 Map，你也会得到各种碎块。所以，当你在切像洋葱这样的蔬菜时，你执行就是一个 Map 操作。Map 操作适用于每一种蔬菜，它会相应地生产出一种或多种碎块，在我们的例子中生产的是蔬菜块。在 Map 操作中可能会出现有个洋葱坏掉了的情况，你只要把坏洋葱丢了就行了。所以，如果出现坏洋葱了，Map 操作就会过滤掉坏洋葱而不会生产出任何的坏洋葱块。

Reduce（化简）:在这一阶段，你将各种蔬菜碎都放入研磨机里进行研磨，你就可以得到一瓶辣椒酱了。这意味要制成一瓶辣椒酱，你得研磨所有的原料。因此，研磨机通常将 map 操作的蔬菜碎聚集在了一起。

妻子： 所以，这就是 Map-Reduce ?
我： 你可以说是，也可以说不是。其实这只是 Map-Reduce 的一部分，Map-Reduce 的强大在于分布式计算。
妻子： 分布式计算？ 那是什么？请给我解释下吧。
我： 没问题。
我： 假设你参加了一个辣椒酱比赛并且你的食谱赢得了最佳辣椒酱奖。得奖之后，辣椒酱食谱大受欢迎，于是你想要开始出售自制品牌的辣椒酱。假设你每天需要生产 10000 瓶辣椒酱，你会怎么办呢？
妻子： 我会找一个能为我大量提供原料的供应商。
我：是的，就是那样的。那你能否独自完成制作呢？也就是说，独自将原料都切碎？ 仅仅一部研磨机又是否能满足需要？而且现在，我们还需要供应不同种类的辣椒酱，像洋葱辣椒酱、青椒辣椒酱、番茄辣椒酱等等。
妻子： 当然不能了，我会雇佣更多的工人来切蔬菜。我还需要更多的研磨机，这样我就可以更快地生产辣椒酱了。
我：没错，所以现在你就不得不分配工作了，你将需要几个人一起切蔬菜。每个人都要处理满满一袋的蔬菜，而每一个人都相当于在执行一个简单的 Map 操作。每一个人都将不断的从袋子里拿出蔬菜来，并且每次只对一种蔬菜进行处理，也就是将它们切碎，直到袋子空了为止。这样，当所有的工人都切完以后，工作台（每个人工作的地方）上就有了洋葱块、番茄块、和蒜蓉等等。
妻子：但是我怎么会制造出不同种类的番茄酱呢？
我：现在你会看到 Map-Reduce 遗漏的阶段：搅拌阶段。Map-Reduce 将所有输出的蔬菜碎都搅拌在了一起，这些蔬菜碎都是在以 key 为基础的 map 操作下产生的。搅拌将自动完成，你可以假设 key 是一种原料的名字，就像洋葱一样。 所以全部的洋葱 keys 都会搅拌在一起，并转移到研磨洋葱的研磨器里。这样，你就能得到洋葱辣椒酱了。同样地，所有的番茄也会被转移到标记着番茄的研磨器里，并制造出番茄辣椒酱。
```

#### 3.3 Single Purpose Aggregation Operations（单一目的的聚合操作）

mongoDB 提供的针对单一集合的聚合操作，例如：

- db.collection.estimatedDocumentCount()  // 返回一个集合中文档的总数
- db.collection.count()
- db.collection.distinct()

举例如下：

![single](shortcut\single.png)

### 4. mongoDB CRUD 操作

#### 4.1 create/insert operation

基本的方法有两个：

- db.collection.insertOne()
- db.collection.insertMany()

例子：

```
db.users.insertOne(
   { name: "Daniel",
     age: 25,
     status: "online"
   }
)
```

#### 4.2 read/query operation

基本方法：

- db.collection.find()

可以指定查询条件。

例子：

```
db.users.find(
  { age: { $gt: 18 } },   // 查询条件
  { name: 1, address: 1 }  // 映射（可参考 2.8）
).limit(5)                 // 游标修饰符
```

#### 4.3 update operation

基本的方法有三个：

- db.collection.updateOne()
- db.collection.updateMany()
- db.collection.replaceOne()

例子：

```
db.users.updateMany(
  { age: { $lt: 18 } },             // 过滤出要更新的 documents
  { $set: { status: "reject" } }    // 更新操作
)
```

#### 4.4 delete operation

基本的方法有两个：

- db.collection.deleteOne()
- db.collection.deleteMany()

例子：

```
db.users.deleteMany(
  { status: "reject" }   // 过滤出要删除的 documents
)
```

