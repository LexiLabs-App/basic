// CANVAS SETTINGS //

class Canvas {
  constructor() {
    // init canvas & context
    this.$canvas = document.querySelector('canvas')
    this.context = this.$canvas.getContext('2d')

    // define screen & mouse properties
    this.screen = {
      width: window.innerHeight,
      height: window.innerHeight,
    }

    this.cursor = {
      x: 0,
      y: 0
    }

   // update canvas size & mouse position
    window.addEventListener('resize', () => this.resize())
    window.addEventListener('mousemove', e => this.updateCursor(e))

    //initiate size of browser
    this.resize()
  }

  resize() {
    this.screen.width = window.innerWidth
    this.screen.height = window.innerHeight

    this.$canvas.width = this.screen.width
    this.$canvas.height = this.screen.height
  }

  updateCursor(_e) {
    this.cursor = {
      x: _e.clientX,
      y: _e.clientY
    }
  }
}

// STAR //

class Star {
  // we define the context in which our stars will appear & their poping position
  constructor(context, positions) {
    this.context = context

    // random angle which our star will navigate to
    this.angle = Math.random() * Math.PI * 3

        // our star properties
    this.star = {
      radius: Math.random(),
      // random colors
      color: `#819BA6`,
      x: positions.x,
      y: positions.y,
      speed: {
        x: Math.cos(this.angle)*7,
        y: Math.sin(this.angle)*7
      }
    }
  }

  // create random between 2 values
  randIn(min, max) {
    return Math.floor(Math.random(max - min) + min)
  }

  // we draw our stars on canvas
  draw() {
    this.star.x += this.star.speed.x
    this.star.y += this.star.speed.y
    this.context.beginPath()
    this.context.arc(
      this.star.x,
      this.star.y,
      (this.star.radius ++) / 60,
      Math.PI * 2,
      false
    )
    this.context.fillStyle = this.star.color
    this.context.shadowColor = this.star.color
    this.context.shadowBlur = 15
    this.context.closePath()
    this.context.fill()
  }
}

// ANIMATION //

class StarAnimation extends Canvas {
  constructor(_params) {
    // finish import Canvas class
    super(_params)

    this.stars = []
    this.starFlow = 5

    // init & animate
    this.loop = this.loop.bind(this)
    this.loop()
  }

  getRandomPos() {
    const res = {
      x: (this.screen.width / 2) + (Math.random()*10),
      y: (this.screen.height / 2) + (Math.random()*10)
    }
    return res
  }

  // the loop function will keep animate each of our stars every frames
  loop() {
    window.requestAnimationFrame(this.loop)

    this.drawBg()
    this.drawStars()
    this.updateStars()
    this.removeOldStars()
  }

  drawBg() {
    this.context.fillStyle = '#223A59'
    this.context.fillRect(0, 0, this.screen.width, this.screen.height)
  }

  // We create a new star, add it to the stars[]
  drawStars() {
    for (let i = 0; i < this.starFlow; i++) {
      const star = new Star(
        this.context,
        this.getRandomPos()
      )
      this.stars.push(star)
    }
  }

  // We redraw all star in stars[]
  updateStars() {
    for (const _star of this.stars) {
      _star.draw()
    }
  }

  // remove star after it disapear from screen
  removeOldStars() {
    setTimeout(() => {
      for (let i = 0; i < this.starFlow; i++) {
        this.stars.shift()
      }
    }, 2500)
  }
}

new StarAnimation()