# ktsyntaxdemo
kotlin syntax :scope function (let ,apply,also,run,with)

 kotlin 没有声明的可见性都为private（静态   除外）
 *
 * 命名：包名小写，类名大写开头（驼峰），方法名小写开头（驼峰，尽量没有下划线）
 *
 * 类名字与内容相关，方法名与方法作用相关
 *
 * 内部类衔接调用代码，如果内部类为外部调用，放在最后
 *
 *空格特殊用法
 *
 * loop 循环，闭合边界使用until
 *
 * scope function let， run， with ，apply ，also
 *
 * context 对象this： apply，run， with;  it：let , also
 * 返回对象 context apply ，also lambda表达式 let ，run， with
 *这5种都是执行对象区域函数，区别是对象的可见性和返回值
 *  run ，let在某种条件下实现效果一致
 *
 *List ---mutablelist,可变list，可以增删改查
 *
 * mutablelist：kotlin的list实现，实现iterable接口（遍历中，可删除元素）
 *
 * mutablelist 不是covariant协变的，类型其子类型不能存入，否则会报错
 *
 *
 * class 类有默认的构造方法，constuctor在没有修饰符时可以隐藏，有修饰符按照修饰符优先级放在constructor前
 * 隐藏默认构造函数，使用private 修饰，新建一个对象，直接调用其构造函数方法，不使用new
 * kotlin 默认类是不可见，如果要外部引用，需要open修饰修改其可见性
 * 匿名内部类，使用object来修饰，必须在局部（或者private修饰下）才能使用，匿名对象表达式作为返回必须声明supertype父类或者any
 *匿名内部类可以访问局部变量
 * 单例声明，使用object来修饰，单例线程安全， 单例不是对象表达式，不能像表达式一样使用，但是直接使用类名来
 *
 * const 编译时常量，条件1：top_level /静态类中，条件2：为String或者基础类型，条件3：无自定义getter
 *
 * lateinit: 不需要初始化、不需要非空判断，使用之前必须初始化，可调用.isInitailized方法来判断初始化
 *
 *  delegated properties:实现一次，val/var name:type by delegate(表达式)，表达式有getvalue/setValue方法，对应get()/set()
 *  getValue()第一个参数为目标参数，第二个参数是描述，setValue()前2个参数和get一样，第三个参数为赋值。
 *  此表达式可以在方法，代码块声明
 *  对于val，必须有含  thisRef（Owner or supertypes）,property(KProperty or subtypes)
 *  对于var，除以上2个参数，第3个参数value（和第一个参数一样）
 *
 *  ::class 反射，格式-类名::class，获取反射对象，是KClass类型的对象（java使用.java）
 *  
 *  ::function 方法作为参数引用
