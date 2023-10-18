关于类继承的一个例子
最后的db.print()需要在Database继承了Item之后才能够使用
因为print是定义在Item类里面的方法
栗子大概就是Database->Item，Item分管CD & DVD两个物品子类