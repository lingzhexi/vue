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

> 　　v-html: 用来获取data中数据将数据中含有的html标签先解析在渲染到指定标签的内部  类似 js 中 innerHTML

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
<div id="app" class="aa">
    <h2>{{age}}</h2>
    <input type="button" value="通过v-on事件修改年龄 +1" v-on:click="changeage">
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
            changeage: function () {
                this.age++;
            },
            editage: function () {
                this.age--;
            }
        }

    });
</script>
```

```markdown
# 总结 
	1. vue事件简化通过 @符号 简化 v-on
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
# 总结
	1.使用事件时，通常调用参数，通过函数接收参数
```

----

## 4.v-show v-if v-bind

### 4.1 v-show

> v-show 用来控制页面中某个元素是否展示  底层控制是标签 display 属性

```html
 <div id="app" class="aa">
    <h2 v-show="false"> v-show</h2>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    new Vue({
        el:'#app',
        data:{},
        methods:{}
    });
</script>
```

### 4.2.V-if

>  控制页面中的标签元素是否展示底层通过对dom树节点进行添加和删除来控制展示和隐藏用

### 4.3.v-bind

>  用来给页面中标签元素绑定相应的属性简化写法v-bin:属性名 ==》:属性名

```html
<div id="app">
    <h2 v-show="false"> v-show</h2>
    <h2 v-show="isShow"> v-show hello</h2>
    <h1 v-if="false">袁老一路走好</h1>
    <h1 v-if="false">吴老一路走好</h1>
    <h1 v-if="false">浪费粮食</h1>
    <img  v-if="isShow"  :src="src" v-bind:title="title"/>
    <input type="button" value="点击我改变title" @click="changeTitle('这是修改后的title')">
    <input type="button" value="点击我改变src" @click="changeSrc('https://gitee.com/lingzhexi/blogImage/raw/master/img2021/05/shiro/20210523165516.png')">
    <br><input type="button" value="点我显示隐藏" @click="showHide">
</div>


<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    new Vue({
        el:'#app',
        data:{
            isShow: true,
            title: '陈小佳',
            src: 'https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png'
        },
        methods:{
            showHide(){
                this.isShow =!this.isShow;
            },
            changeTitle(title) {
                this.title = title;
            },
            changeSrc(src) {
                this.src = src;
            }
        }
    });
</script>
```

----

## 5.v-for使用

> 对象遍历

创建vue模板

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${NAME}</title>


</head>
<body>
<div id="app">

</div>


<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el:'#app',
        data:{},
        methods:{},
        components:{}
    });
</script>
</body>
</html>
```



```html
<div id="app">
    {{user.name}}{{user.age}}
    <br>
    <span v-for="u in user">
        {{u}}
    </span>
    <br>
    <span v-for = "(value,key,index) in user">
        {{key}}:{{value}}
    </span>
    <br>
    <ul>
        <li v-for="a,index in arr">
            {{index}}:{{a}}
        </li>
    </ul>
    <ul>
        <li v-for="u in users":key="u.id">
            {{u.id}}={{u.name}}==={{u.age}}=={{u.state}}
        </li>
    </ul>
</div>


<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data: {
            user:{name:"小岑",age:24},
            arr:["土豆","玉米","小麦"
            ],
            users:[
                {id:1,name:"zhangs",age:25,state:"good"},
                {id:2,name:"lis",age:25,state:"bad"}
            ]
        },
        methods: {},
        components: {}
    });
</script>
```

```markdown
# 总结
在使用-for的时候一定要注意加入:key用来给vue内部提供重用和排序的唯—key
```

----

## 6.v-model 双向绑定

> `v-model`:作用用来绑定标签元素的值与wue实例对象中data数据保持一致,从而实现双向的数据绑定机制

```HTML
<div id="app">
    <input type="text" v-model="message">
    <div>{{message}}</div>
    <input type="button" value="改变输入框的值"@click="changeMsg('凌哲熙')">
</div>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data: {
            message:""
        },
        methods: {
            changeMsg(msg) {
                this.message = msg;
            }
        },
        components: {}
    });
</script>
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
# 1.常用的事件修饰符
    .stop
    .prevent
	.capture
	.self
	.once
	.passive
```

### 7.1 stop事件修饰符

> 用来阻止事件冒泡

```html
<div id="app">
    <div class="aa" @click="divClick">
        <!--阻止事件冒泡-->
        <input type="button" value="按钮" @click.stop="btnClick">
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data: {},
        methods: {
            divClick() {
                alert("div is clicked");
            },
            btnClick() {
                alert('button is clicked');
            }
        },
        components: {}
    });
</script>
```

### 7.2 prevent事件修饰符

> 用来阻止标签的默认行为

```html
  <a href="https://www.bilibili.com" @click="aClick">b站</a>
```

### 7.3 self事件修饰符

>  用来针对于当前标签的事件触发=>只触发自己标签的上特定动作的事件

```html
	<!--只触发标签自身的事件-->
    <div class="aa" @click.self="divClick">
        <!--阻止事件冒泡-->
        <input type="button" value="按钮" @click.stop="btnClick">
        <input type="button" value="按钮1" @click="btnClick1">
    </div>
```

### 7.4 once 事件修饰符

> once 一次 作用：就是让指定事件只触发一次

```html
<!--
.prevent ：用来阻止事件默认的行为
.once    ：用来只执行一次特定的事件
-->
<a href="https://www.baidu.com" @click.prevent.once="aClick">百度</a>
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

