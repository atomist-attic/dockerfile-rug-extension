package com.atomist.rug.kind.dockerfile

import com.atomist.rug.kind.core.{FileMutableView, ProjectMutableView}
import com.atomist.rug.kind.dynamic.ChildResolver
import com.atomist.rug.runtime.rugdsl.{DefaultEvaluator, Evaluator}
import com.atomist.rug.spi.{ReflectivelyTypedType, Type}
import com.atomist.tree.TreeNode

object DockerfileType {
  val dockerfileName = "Dockerfile"
}

class DockerfileType(evaluator: Evaluator)
  extends Type(evaluator)
    with ChildResolver
    with ReflectivelyTypedType {

  import DockerfileType._

  def this() = this(DefaultEvaluator)

  override def description = "Rug language extension for Dockerfile"

  override def runtimeClass = classOf[DockerfileMutableView]

  override def findAllIn(context: TreeNode): Option[Seq[TreeNode]] = context match {
      case pmv: ProjectMutableView =>
        Some(pmv.currentBackingObject.allFiles
          .filter(f => f.name == dockerfileName)
          .map(f => new DockerfileMutableView(f, pmv, new Dockerfile))
        )
      case fmv: FileMutableView if fmv.name == dockerfileName =>
        Some(Seq(new DockerfileMutableView(fmv.currentBackingObject, fmv.parent, new Dockerfile)))
      case _ => None
    }
}
