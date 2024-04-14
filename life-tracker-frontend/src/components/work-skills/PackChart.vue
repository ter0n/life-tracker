<template>
  <svg width="960" height="960"></svg>
</template>

<script setup>
import * as d3 from 'd3'
import jsonData from 'assets/json/flare.json'
import {api} from "boot/axios";
import {computed, onMounted, watchEffect} from "vue";
import {useWorkSkillStore} from "stores/WorkSkill";

const workSkillStore = useWorkSkillStore();

const data2 = computed(() => workSkillStore.workSkills);

watchEffect(() => createPackChart(data2.value?.find(el => el.name === "main node")));

async function createPackChart(data3) {
  let data = null;

  await api.get("/work-skills/get-all").then(response => {
    if (response.status === 200) {
      data = response.data.find(el => el.name === "main node");
    }
  });

  let svg = d3.select("svg"),
    margin = 20,
    diameter = +svg.attr("width"),
    g = svg
      .append("g")
      .attr("transform", "translate(" + diameter / 2 + "," + diameter / 2 + ")");

  let color = d3
    .scaleLinear()
    .domain([-1, 5])
    .range(["hsl(152,80%,80%)", "hsl(228,30%,40%)"])
    .interpolate(d3.interpolateHcl);

  let pack = d3
    .pack()
    .size([diameter - margin, diameter - margin])
    .padding(2);

  // let root = jsonData;
  let root = data3;

  root = d3
    .hierarchy(root)
    .sum(function (d) {
      return d.value;
    })
    .sort(function (a, b) {
      return b.value - a.value;
    });

  let focus = root,
    nodes = pack(root).descendants(),
    view;

  let circle = g
    .selectAll("circle")
    .data(nodes)
    .enter()
    .append("circle")
    .attr("class", function (d) {
      return d.parent
        ? d.children
          ? "node"
          : "node node--leaf"
        : "node node--root";
    })
    .style("fill", function (d) {
      return d.children ? color(d.depth) : null;
    })
    .on("click", function (event) {
      let element = event.srcElement.__data__;
      if (focus !== element) zoom(element, event), event.stopPropagation();
    });


  let text = g
    .selectAll("text")
    .data(nodes)
    .enter()
    .append("text")
    .attr("class", "label")
    .style("fill-opacity", function (d) {
      return d.parent === root ? 1 : 0;
    })
    .style("display", function (d) {
      return d.parent === root ? "inline" : "none";
    })
    .text(function (d) {
      return d.data.name;
    });

  let node = g.selectAll("circle,text");
  svg.style("background", color(-1)).on("click", function (event) {
    zoom(root, event);
  });

  zoomTo([root.x, root.y, root.r * 2 + margin]);

  function zoom(element, event) {
    var focus0 = focus;
    focus = element;

    var transition = d3
      .transition()
      .duration(event.altKey ? 7500 : 750)
      .tween("zoom", function (event) {
        let i = d3.interpolateZoom(view, [
          focus.x,
          focus.y,
          focus.r * 2 + margin
        ]);
        return function (t) {
          zoomTo(i(t));
        };
      });

    transition
      .selectAll("text")
      .filter(function (d) {
        return d.parent === focus || this.style.display === "inline";
      })
      .style("fill-opacity", function (d) {
        return d.parent === focus ? 1 : 0;
      })
      .on("start", function (d) {
        if (d.parent === focus) this.style.display = "inline";
      })
      .on("end", function (d) {
        if (d.parent !== focus) this.style.display = "none";
      });
  }

  function zoomTo(v) {
    let k = diameter / v[2];
    view = v;
    node.attr("transform", function (d) {
      return "translate(" + (d.x - v[0]) * k + "," + (d.y - v[1]) * k + ")";
    });
    circle.attr("r", function (d) {
      return d.r * k;
    });
  }
}
</script>

<style>

.node {
  cursor: pointer;
}

.node:hover {
  stroke: #000;
  stroke-width: 1.5px;
}

.node--leaf {
  fill: white;
}

.label {
  font: 11px "Helvetica Neue", Helvetica, Arial, sans-serif;
  text-anchor: middle;
  text-shadow: 0 1px 0 #fff, 1px 0 0 #fff, -1px 0 0 #fff, 0 -1px 0 #fff;
}

.label,
.node--root,
.node--leaf {
  pointer-events: none;
}

</style>
