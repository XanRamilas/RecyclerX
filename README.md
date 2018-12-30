# Recycler X: Expandable RecyclerView for Android with one level of the nesting
___
This is a small library that allows you to create drop-down lists for **Android** applications with one nesting level inside **RecyclerView**.

### Version 1.x
* Current stable version **1.0**.

### Features
* No dependencies on third-party libraries and frameworks;
* Support of **Android** from version 4 (API 14);
* An ability to create and use any type of **RecyclerView.ViewHolder**;
* Can be used as a normal RecyclerView without the use of nesting levels;
* Library size is 32 Kb.

### Demo

![](https://media.giphy.com/media/cGu1uphaea8Ksph0ZD/giphy.gif)

## Installation
___
Currently, you can install a library in your project in the following way: download this repository and embed a **recyclerx** module into your project. Installation using Maven Central will be available soon.

### Installing using Gradle
1. Copy the **recyclerx** directory to your project.
2. Locate a file **settings.gradle**.  
![tutorial_1.png](https://bitbucket.org/repo/ypjGkpy/images/623732846-tutorial_1.png)
	* The file code might look something like this:  
	```include ':app'```
	* Declare the module **recyclerx** in this file:  
	```include ':app', ':recyclerx'```
3. Now the **recyclerx** module is implemented in your project. It remains to add a dependency on it.
	* Select the module to which the dependency to **recyclerx** should be connected. For example, this is the ```app``` module.
	* Find the **build.gradle** module (not the **build.gradle** project), to which you are connecting the dependency.
	![tutorial_2.png](https://bitbucket.org/repo/ypjGkpy/images/4100770009-tutorial_2.png)
	* Add to **dependencies** the following line:  
	```implementation project(':recyclerx')```
	* **dependencies** of the module where you have added the dependency to **recyclerx** might look line:  
	![tutorial_3.png](https://bitbucket.org/repo/ypjGkpy/images/59272989-tutorial_3.png)
4. Done!

## Fast start
___

1) As in a usual **ReyclerView**, first you need to create a ```RecyclerView.ViewHolder```. To do this, simply inherit from a ```BaseViewHolder<T>``` class.  
Example:  
```
MyAwesomeViewHolder extends BaseViewHolder<MyAwesomeEntity>

@Override
public void bindViewHolderWithEntity(@Nullable MyAwesomeEntity entity) {
	textView.setText(entity.getAwesomeText());
}
```

2) Then create a class that will bind the data to the view. To do this, inherit from the ```BaseViewHolderBinder<T>``` class.  
Example:  
```
MyAwesomeViewHolderBinder extends BaseViewHolderBinder<MyAwesomeEntity>

	@Override
	public void bindViewHolderWithItem(@NonNull RecyclerView.ViewHolder viewHolder) {
		MyAwesomeViewHolder castedHolder = (MyAwesomeViewHolder) viewHolder;
		castedHolder.bindViewHolderWithEntity(getItem());
}
```

3) Next, you need to impelemnt interfaces ```ViewHolderFactory``` and ```ViewHolderBinderFactory<B extends BaseViewHolderBinder, T>```.  
3.1 ```ViewHolderFactory``` acts as a factory for creating **RecyclerView.ViewHolder**. So, for our ```MyAwesomeViewHolder```, we need to implement the interface that our **ViewHolder** will create.  
Example:  
```
MyAwesomeViewHolderFactory implemets ViewHolderFactory {

	@Override
	RecyclerView.ViewHolder createViewHolder(@NonNull View itemView,
                                             @NonNull RecyclerViewItemClickListener listener) {
		return new MyAwesomeViewHolder(itemView, listener);
	}
}
```
3.2 ```ViewHolderBinderFactory<B extends BaseViewHolderBinder, T>``` is a binder creating factory.  
Example:  
```
MyAwesomeViewHolderBinderFactory implements ViewHolderBinderFactory<MyAwesomeViewHolder, MyAwesomeEntity> {

	private static final int VIEW_TYPE = R.layout.my_awesome_layout_item;

	@Override
	MyAwesomeViewHolderBinderFactory createViewHolderBinder(MyAwesomeEntity entity) {
		return new MyAwesomeViewHolderBinder(VIEW_TYPE, entity);
	}
	
	@Override
	boolean isForClass(Class aClass) {
		return MyAwesomeEntity.class.equals(aClass);
	}
}
```

4) After that you need to implement a ```Dispatcher<B extends BaseViewHolderBinder, T, F extends ViewHolderBinderFactory>``` interface.
The implementation of this interface will allow you to create a mechanism by which you can automatically determine which ViewHolderBinder you need for a specific model (entity).  
Example:  
```
MyAwesomeDespatcher implements Dispatcher<MyAwesomeViewHolderBinder, MyAwesomeEntity, MyAwesomeViewHolderBinderFactory> {

 @Override
 List<MyAwesomeViewHolderBinder> dispatchAndBind(List<MyAwesomeEntity> items, List<MyAwesomeViewHolderBinderFactory> factories) {
 	List<BaseViewHolderBinder> binders = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            MyAwesomeEntity entity = items.get(i);
            for (int j = 0; j < factories.size(); j++) {
                if (factories.get(j).isForClass(entity.getClass())) {
                    binders.add(factories.get(j).createViewHolderBinder(entity));
                    break;
                }
            }
        }

        return binders;
 	}
}
```

5) Now let's move on to creating a delegate of our section. Within one section there can be any number of entities and ViewHolders. Imagine a delegate like a container that stores information about the section itself and all its data.
The delegate has a repository that stores entities (by default, there is a ready-made implementation with a simple set of ```BaseSectionRepository<T>``` methods).
At the base of any delegate there is a section. But there might not be any section as an entity. For example, if you need a list with different types of elements without nesting, then you can specify  ```null``` as a section.  
In the simplest sense, a section is a simple entity that stores two states with the help of a flag: the section is opened or the section is closed (collapsed). To create your section entity, inherit from the ```Section``` class.
Finally, inherit from the ```BaseSectionDelegate<S extends Section, T>``` class and implement all the methods as you need. No need to describe it detais here. This repository has **sample**, which implements three delegates at once as an example.
It is important to understand that one delegate == one section. Within the limits of one delegate there can not be two or more sections.  
The example that I am demonstrating now is a simple list of one delegate without a section (otherwise the example would be even more complicated).

6) So, the penultimate stage of creating a list and transferring it to the outside becomes the implementation of an aggregator. The aggregator collects all the delegates of the sections, which there might be, and sends this information to **RecyclerView.Adapter**.
In order to create an aggregator, inherit from the ```BaseSectionAggregator``` class and implement two methods.  
Example:  
```
@Override
Map<Integer, ViewHolderFactory> createViewHolderFactories() {
	Map<Integer, ViewHolderFactory> factories = new HashMap<>();

	for (BaseSectionDelegate delegate : getDelegates()) {
		factories.putAll(delegate.createViewHolderFactories());
	}

	return factories;
}

@Override
List<BaseViewHolderBinder> createViewHolderBinders() {
	List<BaseViewHolderBinder> binders = new ArrayList<>();

	for (BaseSectionDelegate delegate : getDelegates()) {
		binders.addAll(delegate.createAllBinders());
	}

	return binders;
}
```

7) Finally, create an instance of a ```RecyclerXAdapter``` class. You can actually use ```RecyclerXAdapter``` out of the box, or inherit from it, or create your own from scratch.  
Example:  
```
RecyclerXAdapter myAwesomeAdapter = new RecyclerXAdapter(mMyAwesomeSectionAggregator);
mRecyclerView.setAdapter(myAwesomeAdapter);
```

That's all! You are well done in passing difficult training! :-)  
  
You probably have questions. Therefore, in order to better understand how to use **recyclerx** in practice, I recomended to look at **sample**, that you can find in this repository.

## LICENSE
___
```
Copyright © 2018, Vadim Firsov.  

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```