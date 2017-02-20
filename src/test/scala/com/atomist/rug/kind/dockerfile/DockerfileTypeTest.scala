package com.atomist.rug.kind.dockerfile

import com.atomist.rug.runtime.rugdsl.DefaultEvaluator
import org.scalatest.{FlatSpec, Matchers}

class DockerfileTypeTest extends FlatSpec with Matchers {

  import DockerfileMutableViewTest._

  val typo = new DockerfileType(DefaultEvaluator)

  it should "be able to find the language in the project" in {
    val mvs = typo.findAllIn(pmv) match {
      case Some(tns) => tns
      case _ => fail(s"failed to find any Dockerfile in project")
    }
    assert(mvs.size === 1)
    assert(mvs.head.nodeName === "Dockerfile")
  }

  it should "be able to find the language in the file" in {
    val mvs = typo.findAllIn(fmv) match {
      case Some(tns) => tns
      case _ => fail(s"failed to extract Dockerfile from file")
    }
    assert(mvs.size === 1)
    assert(mvs.head.nodeName === "Dockerfile")
  }
}
