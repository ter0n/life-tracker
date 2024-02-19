<template>
  <div>
    <h2>Vue.js and D3 Line Chart</h2>
    <svg id="svg" width="960" height="960"></svg>
  </div>
</template>

<script>

import * as d3 from 'd3'
import jsonData from '../assets/json/flare.json'
export default {
  name: "TestPack",
  components: {},
  mounted() {

    let svg = d3.select('svg');
    // let svg = d3.hierarchy(data);
    let width = +svg.attr('width');
    let height = +svg.attr('height');

    let radius = 15;

    let nodesData = [
      { 'name': 'Lillian', 'sex': 'F' },
      { 'name': 'Gordon', 'sex': 'M' },
      { 'name': 'Sylvester', 'sex': 'M' },
      { 'name': 'Mary', 'sex': 'F' },
      { 'name': 'Helen', 'sex': 'F' },
      { 'name': 'Jamie', 'sex': 'M' },
      { 'name': 'Jessie', 'sex': 'F' },
      { 'name': 'Ashton', 'sex': 'M' },
      { 'name': 'Duncan', 'sex': 'M' },
      { 'name': 'Evette', 'sex': 'F' },
      { 'name': 'Mauer', 'sex': 'M' },
      { 'name': 'Fray', 'sex': 'F' },
      { 'name': 'Duke', 'sex': 'M' },
      { 'name': 'Baron', 'sex': 'M' },
      { 'name': 'Infante', 'sex': 'M' },
      { 'name': 'Percy', 'sex': 'M' },
      { 'name': 'Cynthia', 'sex': 'F' },
      { 'name': 'Feyton', 'sex': 'M' },
      { 'name': 'Lesley', 'sex': 'F' },
      { 'name': 'Yvette', 'sex': 'F' },
      { 'name': 'Maria', 'sex': 'F' },
      { 'name': 'Lexy', 'sex': 'F' },
      { 'name': 'Peter', 'sex': 'M' },
      { 'name': 'Ashley', 'sex': 'F' },
      { 'name': 'Finkler', 'sex': 'M' },
      { 'name': 'Damo', 'sex': 'M' },
      { 'name': 'Imogen', 'sex': 'F' }
    ];

    // Sample links data
    // type: A for Ally, E for Enemy
    let linksData = [
      { 'source': 'Sylvester', 'target': 'Gordon', 'type': 'A' },
      { 'source': 'Sylvester', 'target': 'Lillian', 'type': 'A' },
      { 'source': 'Sylvester', 'target': 'Mary', 'type': 'A' },
      { 'source': 'Sylvester', 'target': 'Jamie', 'type': 'A' },
      { 'source': 'Sylvester', 'target': 'Jessie', 'type': 'A' },
      { 'source': 'Sylvester', 'target': 'Helen', 'type': 'A' },
      { 'source': 'Helen', 'target': 'Gordon', 'type': 'A' },
      { 'source': 'Mary', 'target': 'Lillian', 'type': 'A' },
      { 'source': 'Ashton', 'target': 'Mary', 'type': 'A' },
      { 'source': 'Duncan', 'target': 'Jamie', 'type': 'A' },
      { 'source': 'Gordon', 'target': 'Jessie', 'type': 'A' },
      { 'source': 'Sylvester', 'target': 'Fray', 'type': 'E' },
      { 'source': 'Fray', 'target': 'Mauer', 'type': 'A' },
      { 'source': 'Fray', 'target': 'Cynthia', 'type': 'A' },
      { 'source': 'Fray', 'target': 'Percy', 'type': 'A' },
      { 'source': 'Percy', 'target': 'Cynthia', 'type': 'A' },
      { 'source': 'Infante', 'target': 'Duke', 'type': 'A' },
      { 'source': 'Duke', 'target': 'Gordon', 'type': 'A' },
      { 'source': 'Duke', 'target': 'Sylvester', 'type': 'A' },
      { 'source': 'Baron', 'target': 'Duke', 'type': 'A' },
      { 'source': 'Baron', 'target': 'Sylvester', 'type': 'E' },
      { 'source': 'Evette', 'target': 'Sylvester', 'type': 'E' },
      { 'source': 'Cynthia', 'target': 'Sylvester', 'type': 'E' },
      { 'source': 'Cynthia', 'target': 'Jamie', 'type': 'E' },
      { 'source': 'Mauer', 'target': 'Jessie', 'type': 'E' },
      { 'source': 'Duke', 'target': 'Lexy', 'type': 'A' },
      { 'source': 'Feyton', 'target': 'Lexy', 'type': 'A' },
      { 'source': 'Maria', 'target': 'Feyton', 'type': 'A' },
      { 'source': 'Baron', 'target': 'Yvette', 'type': 'E' },
      { 'source': 'Evette', 'target': 'Maria', 'type': 'E' },
      { 'source': 'Cynthia', 'target': 'Yvette', 'type': 'E' },
      { 'source': 'Maria', 'target': 'Jamie', 'type': 'E' },
      { 'source': 'Maria', 'target': 'Lesley', 'type': 'E' },
      { 'source': 'Ashley', 'target': 'Damo', 'type': 'A' },
      { 'source': 'Damo', 'target': 'Lexy', 'type': 'A' },
      { 'source': 'Maria', 'target': 'Feyton', 'type': 'A' },
      { 'source': 'Finkler', 'target': 'Ashley', 'type': 'E' },
      { 'source': 'Sylvester', 'target': 'Maria', 'type': 'E' },
      { 'source': 'Peter', 'target': 'Finkler', 'type': 'E' },
      { 'source': 'Ashley', 'target': 'Gordon', 'type': 'E' },
      { 'source': 'Maria', 'target': 'Imogen', 'type': 'E' }
    ];

    // set up the simulation and add forces
    let simulation = d3.forceSimulation()
      .nodes(nodesData)

    let linkForce = d3.forceLink(linksData)
      .id(function (d) { return d.name })

    let chargeForce = d3.forceManyBody()
      .strength(-100)

    let centerForce = d3.forceCenter(width / 2, height / 2)

    simulation
      .force('chargeForce', chargeForce)
      .force('centerForce', centerForce)
      .force('links', linkForce)

    // add tick instructions:
    simulation.on('tick', tickActions)

    // add encompassing group for the zoom
    let g = svg.append('g')
      .attr('class', 'everything')

    // draw lines for the links
    let link = g.append('g')
      .attr('class', 'links')
      .selectAll('line')
      .data(linksData)
      .enter().append('line')
      .attr('stroke-width', 2)
      .style('stroke', linkColour)

    // draw circles for the nodes
    let node = g.append('g')
      .attr('class', 'nodes')
      .selectAll('circle')
      .data(nodesData)
      .enter()
      .append('circle')
      .attr('r', radius)
      .attr('fill', circleColour);


    function circleColour (d) {
      if (d.sex === 'M') {
        return 'blue'
      } else {
        return 'pink'
      }
    }
    function linkColour (d) {
      if (d.type === 'A') {
        return 'green'
      } else {
        return 'red'
      }
    }

    function tickActions () {
      // update circle positions each tick of the simulation
      node
        .attr('cx', function (d) { return d.x })
        .attr('cy', function (d) { return d.y })

      // update link positions
      link
        .attr('x1', function (d) { return d.source.x })
        .attr('y1', function (d) { return d.source.y })
        .attr('x2', function (d) { return d.target.x })
        .attr('y2', function (d) { return d.target.y })
    }

    // let svg = d3.select('svg'),
    //   margin = 20,
    //   diameter = +svg.attr("width"),
    //   g = svg
    //     .append("g")
    //     .attr("transform", "translate(" + diameter / 2 + "," + diameter / 2 + ")");
    //
    // let color = d3
    //   .scaleLinear()
    //   .domain([-1, 5])
    //   .range(["hsl(152,80%,80%)", "hsl(228,30%,40%)"])
    //   .interpolate(d3.interpolateHcl);
    //
    // let pack = d3
    //   .pack()
    //   .size([diameter - margin, diameter - margin])
    //   .padding(2);
    //
    //
    //
    // d3.json("https://raw.githubusercontent.com/akeyz/datasets/master/flare.json", function (error, root) {
    //   if (error) throw error;
    //
    //   root = d3
    //     .hierarchy(root)
    //     .sum(function (d) {
    //       return d.size;
    //     })
    //     .sort(function (a, b) {
    //       return b.value - a.value;
    //     });
    //
    //   let focus = root,
    //     nodes = pack(root).descendants(),
    //     view;
    //
    //
    //
    //   let circle = g
    //     .selectAll("circle")
    //     .data(nodes)
    //     .enter()
    //     .append("circle")
    //     .attr("class", function (d) {
    //       return d.parent
    //         ? d.children
    //           ? "node"
    //           : "node node--leaf"
    //         : "node node--root";
    //     })
    //     .style("fill", function (d) {
    //       return d.children ? color(d.depth) : null;
    //     })
    //     .on("click", function (d) {
    //       if (focus !== d) zoom(d), d3.event.stopPropagation();
    //     });
    //
    //   let text = g
    //     .selectAll("text")
    //     .data(nodes)
    //     .enter()
    //     .append("text")
    //     .attr("class", "label")
    //     .style("fill-opacity", function (d) {
    //       return d.parent === root ? 1 : 0;
    //     })
    //     .style("display", function (d) {
    //       return d.parent === root ? "inline" : "none";
    //     })
    //     .text(function (d) {
    //       return d.data.name;
    //     });
    //
    //   let node = g.selectAll("circle,text");
    //
    //   // svg.style("background", color(-1)).on("click", function () {
    //   //   zoom(root);
    //   // });
    //
    //   zoomTo([root.x, root.y, root.r * 2 + margin]);

    // function zoom(d) {
    //   let focus0 = focus;
    //   focus = d;
    //
    //   let transition = d3
    //     .transition()
    //     .duration(d3.event.altKey ? 7500 : 750)
    //     .tween("zoom", function (d) {
    //       let i = d3.interpolateZoom(view, [
    //         focus.x,
    //         focus.y,
    //         focus.r * 2 + margin
    //       ]);
    //       return function (t) {
    //         zoomTo(i(t));
    //       };
    //     });
    //
    //   transition
    //     .selectAll("text")
    //     .filter(function (d) {
    //       return d.parent === focus || this.style.display === "inline";
    //     })
    //     .style("fill-opacity", function (d) {
    //       return d.parent === focus ? 1 : 0;
    //     })
    //     .on("start", function (d) {
    //       if (d.parent === focus) this.style.display = "inline";
    //     })
    //     .on("end", function (d) {
    //       if (d.parent !== focus) this.style.display = "none";
    //     });
    // }

    // function zoomTo(v) {
    //   let k = diameter / v[2];
    //   view = v;
    //   node.attr("transform", function (d) {
    //     return "translate(" + (d.x - v[0]) * k + "," + (d.y - v[1]) * k + ")";
    //   });
    //   circle.attr("r", function (d) {
    //     return d.r * k;
    //   });
    // }
    // });
    console.log("svg: ", svg);
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

