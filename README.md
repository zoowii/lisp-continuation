lisp-continuation
=================

一个f(env, continuation) = (env2, continuation2)的lisp实现demo，只为了表示lisp的continuation机制的一种实现方式

## 说明
* 本项目的相关说明请参看项目wiki和我的博客[Scheme的continuation的实现方式(SchemePy)](http://www.zoowii.com/2014/01/schemecontinuationschemepy.html)等博客
* 目前只是原型实现，甚至不一定能运行，代码中有许多TODO和注释说明，主要目的是用代码描述基于f(env, cont)=(env2, cont2)的实现scheme方言的方式
* 因为本项目只是为了说明scheme的continuation机制怎么实现，所以没有宏，reader-macro，词法作用域，尾递归优化等特性
* 如有疑问，请邮件联系我
