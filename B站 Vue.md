# Vue后端开发教程

## 1.Vue 入门

### 1.1 下载 Vuejs

```html
//开发版本
<!-- 开发环境版本，包含了有帮助的命令行警告 -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

//生产版本
<!-- 生产环境版本，优化了尺寸和速度 -->
<script src="https://cdn.jsdelivr.net/npm/vue"></script>
```

###  1.2 Vue第一个应用

```vue
<div id="app">
    {{msg}}  {{username}} {{pwd}}
</div>

<!--建议放到临近的js位置-->
<!--1.引入vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<!--2.创建vue实例-->
<script>
    const app = new Vue({
        el: '#app',         //用来给vue实例定义一个作用范围
        data: {         //用来给vue实例定义一些相关数据
            msg:"你好，欢迎这个世界！",
            username:"hello vue",
            pwd: "1234"
        },
    });
</script>
```



```markdown
# 总结：
	1.vue实例（对象）中el属性：代表Vue的作用范围    日后在Vue的作用范围内都可以使用Vue语法
	2.vue实例（对象）中的data：绑定一些相关的数据变量，绑定的数据通过{{变量名}}在Vue的作用范围内取出
	3.在使用{{}}进行获取data中数据时，可在{{}}写表达式，运算符，调用相关方法，以及逻辑运算等
	4.el属性中可以书写任意css选择器（jq选择器），但是使用Vue开发是推荐使用，id选择器
```

----

## 2.vue-text和v-html

2.1 v-text

> v-text : 用来获取data中数据将数据以文本的形式渲染到指定的标签内部 类似 js中的 innerText

```html
<div id="app" class = "aa">
    <!--差值表达器-->
    <span>{{msg}}</span><br>
    <span v-text="msg"></span>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el: '.aa',
        data: {
            msg: "hello world"

        }
    });
```

```markdown
#  总结
		1.{{}}（插值表达）和v-text获取数据的区别  
			a.使用v-text取值会将标签中原有的数据覆盖  插值表达其不会
			b.使用v-text可以避免在网络环境较差的情况出现插值闪烁
```

### 2.2 v-html

> 　　　　v-html: 用来获取data中数据将数据中含有的html标签先解析在渲染到指定标签的内部  类似 js 中 innerHTML

```html
<div id="app" class="aa">

    <span>{{msg}}</span><br>
    <span v-text="msg"></span>
    <br>
    <span v-html="msg"></span>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data: {
            msg: "<a href=''> 欢迎你!</a>"

        }
    });
```

----

## 3.vue中时间绑定（v-on） 

### 3.1 绑定事件语法

```html
<div id="app" class = "aa">
    <h2>{{msg}}</h2>
    <h2 v-text="msg"></h2>
    <span>年龄：{{age}}</span>
    <br>
    <input type="button" value="点击改变年龄" v-on:click="changeAge">
</div>

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data: {
            msg: "hello world",
            age: "23"
        },
        methods:{ //定义vue事件
            changeAge:function (){
                alert("出发")
            }
        }
    });
</script>
```

```markdown
# 总结：
	事件   事件源:发送事件demo元素   事件：发生特定的动作 click...   监听器  发生特定动作之后的事件处理程序通常时js中的函数
	1.在vue中绑定事件时通过v-on指令完成的  v-on:事件名  如 v-on:click
	2.在v-on:事件名的赋值语句中的当前事件触发的函数名
	3.在vue中事件的函数统一定义在Vue实例的methods属性中
	4.在vue定义事件中this值的就是当前Vue实例，日后可以在事件中通过使用this获取Vue中相关数据
```

### 3.2 vue事件简化写法

```html
<div id="app" class="aa">    <h2>{{age}}</h2>    <input type="button" value="通过v-on事件修改年龄 +1" v-on:click="changeage">    <input type="button" value="通过@事件修改年龄 -1" @click="editage"></div><script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script><script>    const app = new Vue({        el: '#app',        data:{            age:20        },        methods: {            changeage: function () {                this.age++;            },            editage: function () {                this.age--;            }        }    });</script>
```

```markdown
# 总结 	1. vue事件简化通过 @符号 简化 v-on
```

### 3.3 Vue优化写法

```html
<div id="app" class="aa">
    <h2>{{age}}</h2>
    <input type="button" value="通过@事件修改年龄 -1" @click="editage">
</div>

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data:{
            age:20
        },
        methods: {
            // changeage: function () {
            //     this.age++;
            // },
            editage() {
                this.age--;
            }
        }

    });
</script>
```

```markdown
# 总结
 	1.事件定义存在两种写法 1.函数名:function(){} 2.函数名(){}推荐
```

### 3.4 Vue事件传递

```html
<div id="app" class="aa">
    <h2>{{age}}</h2>
    <input type="button" value="事件传值" @click="changeage(23,'xiaochen')">
</div>

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data:{
            age:20
        },
        methods: {
            changeage(age) {
                this.age = age;
            },
        }

    });
</script>
```



```markdown
# 总结	1.使用事件时，通常调用参数，通过函数接收参数
```

----

## 4.v-show v-if v-bind

### 4.1 v-show

> v-show 用来控制页面中某个元素是否展示  底层控制是标签 display 属性

```html
 <div id="app" class="aa">    <h2 v-show="false"> v-show</h2></div><script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script><script>    new Vue({        el:'#app',        data:{},        methods:{}    });</script>
```

### 4.2.V-if

>  控制页面中的标签元素是否展示底层通过对dom树节点进行添加和删除来控制展示和隐藏用

### 4.3.v-bind 

>  用来给页面中标签元素绑定相应的属性简化写法v-bin:属性名 ==》:属性名

```html
<div id="app">    <h2 v-show="false"> v-show</h2>    <h2 v-show="isShow"> v-show hello</h2>    <h1 v-if="false">袁老一路走好</h1>    <h1 v-if="false">吴老一路走好</h1>    <h1 v-if="false">浪费粮食</h1>    <img  v-if="isShow"  :src="src" v-bind:title="title"/>    <input type="button" value="点击我改变title" @click="changeTitle('这是修改后的title')">    <input type="button" value="点击我改变src" @click="changeSrc('https://gitee.com/lingzhexi/blogImage/raw/master/img2021/05/shiro/20210523165516.png')">    <br><input type="button" value="点我显示隐藏" @click="showHide"></div><script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script><script>    new Vue({        el:'#app',        data:{            isShow: true,            title: '陈小佳',            src: 'https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png'        },        methods:{            showHide(){                this.isShow =!this.isShow;            },            changeTitle(title) {                this.title = title;            },            changeSrc(src) {                this.src = src;            }        }    });</script>
```

----

## 5.v-for使用

> 对象遍历

创建vue模板

```html
<!DOCTYPE html><html lang="en"><head>    <meta charset="UTF-8">    <title>${NAME}</title></head><body><div id="app"></div><script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script><script>    const app = new Vue({        el:'#app',        data:{},        methods:{},        components:{}    });</script></body></html>
```



```html
<div id="app">    {{user.name}}{{user.age}}    <br>    <span v-for="u in user">        {{u}}    </span>    <br>    <span v-for = "(value,key,index) in user">        {{key}}:{{value}}    </span>    <br>    <ul>        <li v-for="a,index in arr">            {{index}}:{{a}}        </li>    </ul>    <ul>        <li v-for="u in users":key="u.id">            {{u.id}}={{u.name}}==={{u.age}}=={{u.state}}        </li>    </ul></div><script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script><script>    const app = new Vue({        el: '#app',        data: {            user:{name:"小岑",age:24},            arr:["土豆","玉米","小麦"            ],            users:[                {id:1,name:"zhangs",age:25,state:"good"},                {id:2,name:"lis",age:25,state:"bad"}            ]        },        methods: {},        components: {}    });</script>
```

```markdown
# 总结在使用-for的时候一定要注意加入:key用来给vue内部提供重用和排序的唯—key
```

----

## 6.v-model 双向绑定

> `v-model`:作用用来绑定标签元素的值与wue实例对象中data数据保持一致,从而实现双向的数据绑定机制

```HTML
<div id="app">    <input type="text" v-model="message">    <div>{{message}}</div>    <input type="button" value="改变输入框的值"@click="changeMsg('凌哲熙')"></div><script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script><script>    const app = new Vue({        el: '#app',        data: {            message:""        },        methods: {            changeMsg(msg) {                this.message = msg;            }        },        components: {}    });</script>
```

```markdown
# 总结
	1.使用v- model指令可以实现数据的双向绑定
	2.所谓双向绑定表单中数据变化导致vue实例data数据变化vue实例中data数据的变化导致表单中数据变化称之为双向绑定
# MVWM架构  双向绑定机制
	Model:数据vue   实例中绑定数据
	VM: ViewMode1   监听器
	View:页面		  页面展示的数据
```



### 练习：记事本案例

```html
<div id="app">
    <input type="text" v-model="msg"> <input type="button" value="添加到记事本" @click="save()">
    <br>
    <ul>
        <li v-for="item,index in lists">
            {{index+1}}: {{item}} <a href="javascript:" @click="delItem(index)">删除</a>
        </li>
    </ul>
    <span>总数量：{{lists.length}} 条</span> <input type="button" v-show="lists.length!=0" value="删除所有" @click="delAll()">
</div>


<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data: {
            lists:['今天下了一场暴雨','晚上回家吃小龙虾'],
            msg:""
        },
        methods: {
            save() {
                if (''!=this.msg) {
                    this.lists.push(this.msg);
                    this.msg = '';
                }
            },
            delItem(index) {
                this.lists.splice(index, 1);
            },
            delAll() {
                this.lists = [];
            }
        },
        components: {}
    });
</script>
```

----

## 7.事件修饰符

> `修饰符`: 作用用来和事件连用,用来决定事件触发条件或者是阻止事件的触发机制

```markdown
# 1.常用的事件修饰符    .stop    .prevent	.capture	.self	.once	.passive
```

### 7.1 stop事件修饰符

> 用来阻止事件冒泡

```html
<div id="app">    <div class="aa" @click="divClick">        <!--阻止事件冒泡-->        <input type="button" value="按钮" @click.stop="btnClick">    </div></div><script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script><script>    const app = new Vue({        el: '#app',        data: {},        methods: {            divClick() {                alert("div is clicked");            },            btnClick() {                alert('button is clicked');            }        },        components: {}    });</script>
```

### 7.2 prevent事件修饰符

> 用来阻止标签的默认行为

```html
  <a href="https://www.bilibili.com" @click="aClick">b站</a>
```

### 7.3 self事件修饰符

>  用来针对于当前标签的事件触发=>只触发自己标签的上特定动作的事件

```html
	<!--只触发标签自身的事件-->    <div class="aa" @click.self="divClick">        <!--阻止事件冒泡-->        <input type="button" value="按钮" @click.stop="btnClick">        <input type="button" value="按钮1" @click="btnClick1">    </div>
```

### 7.4 once 事件修饰符

> once 一次 作用：就是让指定事件只触发一次

```html
<!--.prevent ：用来阻止事件默认的行为.once    ：用来只执行一次特定的事件--><a href="https://www.baidu.com" @click.prevent.once="aClick">百度</a>
```

----

## 8.按键修饰符

>  作用:用来与键盘中按键事件绑定在一起,用来修饰特定的按键事件的修饰符

```markdown
# 按键修饰符
	.enter
	.tab
	.delete (捕获“删除”和“退格”键)
	.esc
	.space
	.up
	.down
	.left
    .right
```

### 8.1 enter 回车键 

> 用来在触发回车按键之后触发的事件

```html
  <input type="text" v-model="msg" @keyup.enter="keyups">
```

### 8.2 tab

> 用来捕获到tab键执行到当前标签是才会触发

```html
<!--其他光标tab到该文本框的时候触发-->
<input type="text" v-model="msg" @keyup.tab="tabkey">
```

### 8.3 其他

```html
    <input type="text" v-model="msg" @keyup.delete="deleteKey">
    <input type="text" v-model="msg" @keyup.esc="esckey">
    <!--空格-->
    <input type="text" v-model="msg" @keyup.space="spacekey">
    <input type="text" v-model="msg" @keyup.left="leftkey">
    <input type="text" v-model="msg" @keyup.right="rigthkey">
```

## 9.Axios基本使用

### 9.1引言

>  `Axios` 是一个异步请求技术核心作用就是用来在页面中发送异步请求并获取对应数据在页面中渲染页面局部更新技术Aax

### 9.2 Axios第一个程序

​	中文网站：https://www.kancloud.cn/yunye/axios/234845

​	安装：https://unpkg.com/axios/dist/axios.min.js

#### 9.2.1 GET请求

```js
//发送Get请求axios.get("http://localhost:8080/findAll?name=xiaochen").then(function (response){    console.log(response.data);}).catch(function(error){    console.log(error);});
```

#### 9.2.2 POST请求方式

```js
//POST请求
axios.post('http://localhost:8080/save', {
    username: 'xiaochen',
    age: 23,
    email: '5234@qq.com',
    phone: '15352252514'
}).then(function (response) {
    console.log(response.data)
}).catch(function (error) {
    console.log(error);
});
```

#### 9.2.3 axios并发请求

> `并发请求`：将多个请求在同一时刻发送到后端服务接口,最后在集中处理每个请求的响应结果

```js
    //1.创建查询所有请求
    function findAll() {
       return axios.get('http://localhost:8080/findAll?name=xiaochen');
    }
    //2.创建保存请求
    function save() {
        return axios.post('http://localhost:8080/save', {
            username: 'xiaochen',
            email: '5234@qq.com',
            age: 23,
            phone: '15352252514'
        });
    }
    //并发操作
    axios.all([findAll(), save()]).then(
        axios.spread(function (res1, res2) {
            console.log(res1.data);
            console.log(res2.data);
        })
    );//用来发送一组并发请求
```

### 9.2 vue 结合axios查询天气

```html
<div id="app">
    <div>
        <input type="text" v-model="name"@keyup.delete="show" @keyup.enter="searchCity">
        <input type="button" value="搜索" @click="searchCity">
    </div>
    <span v-for="city in citys">
        <a href="" @click.prevent="searchCitys(city)"> {{city}} &nbsp</a>
    </span>
    <hr>
    <span v-show="isShow">{{name}}：今天天气是 {{weather}}</span>
</div>


<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<!--引入axios-->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data: {
            name:'',
            citys:['北京','上海','天津','深圳'],
            weather: '',
            isShow: false
        },
        methods: {
            searchCity(){
                let _this = this
                axios.get("http://localhost:8080/city/find?name="+this.name).then(function (response) {
                    console.log(response.data);
                    _this.weather = response.data.message;
                    _this.isShow = true;
                }).catch(function (error) {
                    console.log(error);
                });

            },
            show() {
                this.isShow = false;
            },
            searchCitys(name) {
                this.name = name;
                this.searchCity();
            }

        },
        components: {}
    });
</script>
```



------

## 10. Vue生命周期

> `生命周期钩子`   ===》 `生命周期函数`

![Vue 实例生命周期](https://cn.vuejs.org/images/lifecycle.png)

```markdown
# Vue生命周期总结
	1.初始化阶段
		  beforeCreate() {//1.生命周期中第一个函数,该函数在执行时vue实例仅仅完成了自身事件的绑定和生命周期函的初始化工作,Vue实例中还没有 Data el methods相关属性
            console.log("beforeCreate:" + this.msg);
        },
        created() {
            /2.生命周期中第二个函数,该函数在执行时vue实例已经初始化了data属性和 methods中相关方法
            console.log("created:" + this.msg);
        },
        beforeMount() {//3.生命周期中第三个函数,该函数在执行时vue将E1中指定作用范围作为模板编译
            console.log(" beforeMount:" + document.getElementById("sp").innerText);
        },
        mounted() {//4.生命周期中第四个函数,该函数在执行过程中,已经将数据渲染到界面中并且已经更新页面
            console.log("Mounted: " + document.getElementById("sp").innerText);
         
        
	2.运行阶段
        beforeUpdate() {//5.生命周期中第五个函数,该函数是data中数据发生变化时执行这个事件执行时仅仅是vue实例中data数据变化页面显示的依然是原始数据
            console.log("beforeUpdate: " + this.msg);
            console.log("beforeUpdate: " + document.getElementById("sp").innerText);
        },
        updated() {//6.生命周期中第六个函数,该函数执行时data中数据发生变化,页面中数据也发生了变化页面中数据已经和data中数据一致
            console.log("updated: " + this.msg);
            console.log("updated: " + document.getElementById("sp").innerText);
        },
        
    3.销毁阶段
        beforeDestory() {//7.生命周期第七个函数,该函数执行时,ue中所有数据 methods componet都没销到
        },
        destoryed() {//8.生命周期的第八个函数,该函数执行时,Wue实例彻底销毁
        }
```

## 11.Vue中的组件( Component)

### 11.1组件作用

​	组件作用:用来减少wue实例对象中代码量,日后在使用wue开发过程中,可以根据不能业务功能将页面中划分不同的多个组件然后由多个组件去完成整个页面的布局便于日后使用
Wue进行开发时页面管理,方便开发人员维护

### 11.2 组件使用

#### 11.2.1 全局组件注册

说明:全局组件注册给vue实例,日后可以在任意ue实例的范围内使用该组件

```js
//1.开发全局组件
    Vue.component('login',(
        template:'<div><h1>用户登录</h1></div>'
    });
//2.使用全局组件在Vue实例范围内
    <login></login>
```

```markdown
# 注意
	1.Vue.component用来开发全局组件  参数1:组件的名称  参数2: 组件配置{} template:'' 用来书写组件的htm1代码 template中必须有且只有一个root元素
    2.使用时需要在vue的作用范围内根据组件名使用全局组件
    3.如果在注册组件过程中使用驼峰命名组件的方式在使用组件时必须将驼峰的所有单词小写加入-线进行使用
```



#### 11.2.2 局部组件注册	

​	`说明:通过将组件注册给对应vue实例中一个 components属性来完成组件注册,这种方式不会对vue实例造成累加`

- 第一种开发

```js
//局部组件登录模板let login = {    template:'<div><h1>用户管理</h1></div>'}const app = new Vue({    el: '#app',    data: {},    methods: {},    components: {        login: login    }});//使用局部组件<login></login>
```

- 第二种开发

```js
//1.声明局部组件模板 template 标签 注意：在Vue实例作用范围外声明	<template id="loginTemplate">        <h1>用户管理</h1>    </template>//2.定义变量用来保存模板配置对象    let login = { //具体组件模板        template: '#loginTemplate' //使用自定义template 标签选择器即可    };//3.注册组件    const app = new Vue({        el: '#app',        data: {},        methods: {},        components: {  //用来注册局部组件            login: login //注册局部组件        }    });//4使用局部组件	<login></login>
```

### 11.3 Prop的使用

`作用: props用来给组件传递相应静态数据或者是动态数据的` 

#### 11.3.1 通过在组件上声明静态数据传递给组件内部

```js
//1.声明组件模板配置对象    let login= {        template:'<div><h1>欢迎：{{username}}年龄：{{age}}<h1></div>',        props:['userName','age'] // props作用用来接收使用组件时通过组件标签传递的数    }//2.注册组件	const app new Vue({        el: '#app',        data: {}        methods: {},        components:{       		login   //组件注册                   }	});//3.通过组件完成数据传递<login user-name="小陈" age="23"></1ogin>
```



```markdown
# 总结    1.使用组件时可以在组件上定义多个属性以及对应数据    2.在组件内部可以使用 props数组生命多个定义在组件上的属性名日后可以在组件中通过{属性名}}方式获取组件中属性值
```

#### 11.3.2 通过在组件上声明动态数据传递给组件内部

```js
//1.声明组件模板对象 const logins = {        template: "<div><h1>welcome {{userName}} ,age {{age}}, email: {{email}},salary {{salary}} !</h1></div>",        props:["userName","age","email","salary"]    } //2.注册局部组件 const app = new Vue({        el:'#app',        data:{			email:'333@qq.com',			salary:'200'		},        methods:{},        components:{            login  //注册组件        }    });//3.使用组件 <login :email="email" :salary="salary" user-name="小陈" age="24"></login>
```

#### 11.3.2 prop的单项数据流

`单向数据流:所有的 prop 都使得其父子prop之间形成了一个单向下行绑定:父级 prop 的更新会向下流动到子组件中,但是反过来则不行`

> 所有的prop都使得其父子prop之间形成了一个单向下行绑定:父级prop的更新会向下流动到子组件中,但是反过来则不行。这样会防止从子组件意外改变父级组件的状
> 态,从而导致你的应用的数据流向难以理解
> 额外的,每次父级组件发生更新时,子组件中所有的prop都将会刷新为最新的值。这意味着你不应该在一个子组件内部改变prop。如果你这样做了,Wue会在浏览器的控
> 制台中发出警告。一摘自官网

### 11.4 组件中定义数据和事件使用

1.组件中定义属于组件的数据

```js
//组件声明的配置对象 const login = {        template: "<div><h1>{{name}} Vue<ul><li v-for='item,index in lists'>{{index+1}}=={{item}}</li></ul></h1></div>",        data() {            return {                name: 'learning',                lists: ['java', 'js', 'python']            }        }    }
```

2.组件定义事件

```js
const login ={
    template: "<div><h1>{{name}}</h1><input type='button' value='点击我修改名称' @click='change'></div>",
    data() {
        return {
            name: "john"
        };
    },
    methods:{
        change() {
            this.name = "micheal";
        }
    }
}
```

```markdown
# 总结
	1.组件中定义事件和直接在vue中定义事件基本一致直接在组件内部对应的htm1代码上加入@事件名=函数名方式即可
	2.在组件内部使用 methods属性用来定义对应的事件函数即可,事件函数中this指向的是当前组件的实例
```

### 11.5 向子组件中传递事件并在子组件中调用改事件

`在子组件中调用传递过来的相关事件必须使用this.$emit(函数名)方式调用`

```js
//1.声明组件
<template id="login">
    <div>
        <h1>Vue {{uname}}</h1>
        <input type='button' value='点我' @click='change'>
    </div>
</template>
const login = {
    template: "#login",
    data() {
        return {
            uname: this.name
        };
    },
    methods: {
        change() {
            this.uname = '点击后触发Vue事件'
            //调用Vue中实例的事件
            this.$emit('fun')/ /调用组件传递过来的其他函数时需要使用this.$emit('函数名调用)
        },

    },
    props: ['name']
}
//2.注册组件
const app = new Vue({
    el: '#app',
    data: {
        username: 'xiaochen'
    },
    methods: {
        fun(){ //一个事件函数  将这个事件函数传递给子组件
            alert('Vue实例的事件')
        }
    },
    components: {
        login,  //组件的注册
    }
});
//3.使用组件
<login :name="username" @fun=fun></login>  //===>在组件内部使用this.$emit("fun")
```

![image-20210602011155939](https://gitee.com/lingzhexi/blogImage/raw/master/img2021/05/jasper/20210602011206.png)

## 12.Vue路由

`注意事项`

![image-20210602014845994](Z:\Mine\vue\B站 Vue.assets\image-20210602014845994-1622575681334.png)

### 12.1 路由

`路由：根据请求的路径按照一定的路由规则进行请求的转发从而帮助我们实现统一的请求管理`

### 12.2 作用

​	`用来在vue中实现组件间的动态切换`

### 12.3 使用

1.引入路由

```js
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script> //Vue 路由js
```

2.创建组件对象

```js
/* 创建模板 */
    <template id="login">
        <h1>登录页面</h1>
    </template>
    <template id="reg">
        <h1>注册页面</h1>
    </template>
    const login = {
        template: '#login'
    }
    const register = {
        template: '#reg'
    }
```

3.定义路由对象的规则

```js
/* 定义路由 */
    const router = new VueRouter({
        routes:[
            {path:"/login",component:login},  //path:路由的路径  component：路由的相应组件
            {path:"/reg",component:register}
        ]
    })
```

4.将路由对象注册到vue实例

```js
//路由注册到Vue实例
const app = new Vue({
    el: '#app',
    data: {},
    methods: {},
    router:router  //设置路由对象

});
```

5.在页面中显示路由的组件

```js
<!-- 显示路由组件 -->
<router-view></router-view>
```

6.根据链接切换路由

```html
<a href="#/login">登录</a>
<a href="#/reg">注册</a>
```

###   12.4  router-link使用

​	`作用：用来替换我们在切换路由时使用a标签切换路由`

​	`好处：就是可以自动给路由路径加入#不需要手动加入`

```html
<router-link to="/login" tag="button">登录</router-link>
<router-link to="/reg" tag="span">注册</router-link>
```



```markdown
# 总结
	1. router-link用来替换使用a标签实现路由切换	好处是不需要书写#号直接书写路由路径
	2. router-1 ink to属性用来书写路由路径	tag属性:用来将 router-1ink渲染成指定的标签
```

### 12.5 默认路由

​	`作用:用来在第一次进入界面是显示一个默认的组件`

```js
/* 定义路由 */
    const router = new VueRouter({
        routes:[
            {path:"/",redirect:'/login'}, // redirect:用来当访问的是默认路由"/"时	跳转到指定的路由展示	推荐使用
            {path:"/login",component:login},
            {path:"/reg",component:register}
        ]
    })
```

### 12.6 路由中参数传递

- 第一种方式传递参数 传统方式

1. 通过?号形式拼接参数

   ```html
   <router-link to="/login?id=1&name=张三" tag="button">i need login</router-link>
   ```

2. 组件中获取参数

   ```js
   const login = {
           template: '#login',
           created() {
               console.log("id: " + this.$route.query.id + "  ==> name:" + this.$route.query.name);
           }
       }
   ```

- 第二种方式传递参数 restful

1. 通过使用路径方式传递

   ```js
   <router-link to="/reg/2/李四" tag="button">i need register</router-link>
   const router = new VueRouter({
       routes: [
           {path: '/reg/:id/:name', component: reg}
       ]
   })
   ```

2. 组件中获取参数

   ```js
   const reg = {
       template: '#reg',
       created() {
           console.log('id: ' + this.$route.params.id + '  ===>name:' + this.$route.params.name);
       }
   }
   ```

   

